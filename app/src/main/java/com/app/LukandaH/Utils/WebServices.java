package com.app.LukandaH.Utils;

import com.app.LukandaH.Handlers.AddToFavoriteHandler;
import com.app.LukandaH.Handlers.BookingHandler;
import com.app.LukandaH.Handlers.CancelBookingHandler;
import com.app.LukandaH.Handlers.ChangeBookingHandler;
import com.app.LukandaH.Handlers.ChangePasswordHandler;
import com.app.LukandaH.Handlers.FilterHandler;
import com.app.LukandaH.Handlers.FinishedBookingHandler;
import com.app.LukandaH.Handlers.ForgotPasswordHandler;
import com.app.LukandaH.Handlers.GetFavoriteHandler;
import com.app.LukandaH.Handlers.GetHotelsHandler;
import com.app.LukandaH.Handlers.GetRatingsHandler;
import com.app.LukandaH.Handlers.HotelSortByHandler;
import com.app.LukandaH.Handlers.HotelViewHandler;
import com.app.LukandaH.Handlers.LatestSearchHandler;
import com.app.LukandaH.Handlers.LoginHandler;
import com.app.LukandaH.Handlers.NearMeHotelsHandler;
import com.app.LukandaH.Handlers.OtpVerifyHandler;
import com.app.LukandaH.Handlers.ProfileHandler;
import com.app.LukandaH.Handlers.ProfileImageHandler;
import com.app.LukandaH.Handlers.ProfileUpdateHandler;
import com.app.LukandaH.Handlers.ResetPasswordHandler;
import com.app.LukandaH.Handlers.SearchByParamtersHandler;
import com.app.LukandaH.Handlers.SearchHandler;
import com.app.LukandaH.Handlers.SignUpHandler;
import com.app.LukandaH.Handlers.SocialLoginHandler;
import com.app.LukandaH.Handlers.UpcomingBookingsHandler;
import com.app.LukandaH.Handlers.WriteReviewHandler;
import com.app.LukandaH.Models.AddToFavoritesModel.AddToFavoriteExample;
import com.app.LukandaH.Models.BookingModels.BookingHotelExample;
import com.app.LukandaH.Models.CancelBookingModels.CancelBookingExample;
import com.app.LukandaH.Models.ChangeBookingModels.ChangeBookingExample;
import com.app.LukandaH.Models.ChangePasswordModels.ChangePasswordExample;
import com.app.LukandaH.Models.FinishedBookingModels.FinishedBookingExample;
import com.app.LukandaH.Models.ForgotPasswordModels.ForgotPasswordExample;
import com.app.LukandaH.Models.GetHotelsModels.GetMeHotelsExample;
import com.app.LukandaH.Models.GetRatingsModels.GetRatingsExample;
import com.app.LukandaH.Models.GetfavoritesModels.GetFavoriteExample;
import com.app.LukandaH.Models.HotelViewModels.HotelViewExample;
import com.app.LukandaH.Models.HotelsSortByModels.HotelSortByExample;
import com.app.LukandaH.Models.LatestSearchModels.LatestSearchExample;
import com.app.LukandaH.Models.LoginModels.LoginExample;
import com.app.LukandaH.Models.NearMeHotelsModel.NearMeHotelsExample;
import com.app.LukandaH.Models.OtpVerifyModels.OtpVerifyExample;
import com.app.LukandaH.Models.ProfileImageModels.ProfileImageExample;
import com.app.LukandaH.Models.ProfileModels.ProfileExample;
import com.app.LukandaH.Models.ProfileUpdateModels.ProfileUpdateExample;
import com.app.LukandaH.Models.ResetPasswordModels.ResetPasswordExample;
import com.app.LukandaH.Models.SearchByParametersModels.SearchByParametersExample;
import com.app.LukandaH.Models.SearchFilterModels.FilterExample;
import com.app.LukandaH.Models.SearchModels.SearchExample;
import com.app.LukandaH.Models.SignUpModels.SignUpExample;
import com.app.LukandaH.Models.SocialLoginModels.SocialLoginExample;
import com.app.LukandaH.Models.UpcomingBookingsModels.UpcomingBookingsExample;
import com.app.LukandaH.Models.WriteReviewModels.WriteReviewExample;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {
    public static final String BASE_URL = "http://93.188.167.68:8006/api/";
    private static final String TAG = "WebServic es";
    private static Retrofit retrofit = null;
    private static WebServices mInstance;
    private API api;
    private String accessToken;

    public WebServices() {
        mInstance = this;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(API.class);
        }
    }

    public static WebServices getmInstance() {
        return mInstance;
    }

    //Login Method
    public void loginMethod(String email, String password, String deviceToken, final LoginHandler loginHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("deviceToken", deviceToken);
        api.loginAPI(jsonObject).enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            loginHandler.onError(message);
                        } else {
                            loginHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                loginHandler.onError(t.getMessage());
            }
        });
    }

    //Social Login Method
    public void socailLoginMethod(String socialLoginId, String platform, String name, String gender, final SocialLoginHandler socialLoginHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("socialLoginId", socialLoginId);
        jsonObject.addProperty("platform", platform);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("gender", gender);
        api.socialLoginAPI(jsonObject).enqueue(new Callback<SocialLoginExample>() {
            @Override
            public void onResponse(Call<SocialLoginExample> call, Response<SocialLoginExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    socialLoginHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            socialLoginHandler.onError(message);
                        } else {
                            socialLoginHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialLoginExample> call, Throwable t) {
                socialLoginHandler.onError(t.getMessage());
            }
        });
    }

    //Sign Up Method
    public void signUpMethod(String name, String email, String phoneNo, String password, String deviceToken, final SignUpHandler signUpHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("phoneNo", phoneNo);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("deviceToken", deviceToken);
        api.signUpAPI(jsonObject).enqueue(new Callback<SignUpExample>() {
            @Override
            public void onResponse(Call<SignUpExample> call, Response<SignUpExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    signUpHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            signUpHandler.onError(message);
                        } else {
                            signUpHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpExample> call, Throwable t) {
                signUpHandler.onError(t.getMessage());
            }
        });
    }

    //User Profile Method
    public void profileMethod(String token, String id, final ProfileHandler profileHandler) {
        api.profileAPI(token, id).enqueue(new Callback<ProfileExample>() {
            @Override
            public void onResponse(Call<ProfileExample> call, Response<ProfileExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    profileHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            profileHandler.onError(message);
                        } else {
                            profileHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileExample> call, Throwable t) {
                profileHandler.onError(t.getMessage());
            }
        });
    }


    public void profileImageUploadMethod(MultipartBody.Part file, String id, final ProfileImageHandler profileImageHandler) {
        api.profileImageAPI(file, id).enqueue(new Callback<ProfileImageExample>() {
            @Override
            public void onResponse(Call<ProfileImageExample> call, Response<ProfileImageExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    profileImageHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            profileImageHandler.onError(message);
                        } else {
                            profileImageHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileImageExample> call, Throwable t) {
                profileImageHandler.onError(t.getMessage());
            }
        });
    }


    //Profile Update API
    public void profileUpdateMethod(String id, String name, String email, String phoneNo, final ProfileUpdateHandler profileUpdateHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("phoneNo", phoneNo);
        api.profileUpdateAPI(id, jsonObject).enqueue(new Callback<ProfileUpdateExample>() {
            @Override
            public void onResponse(Call<ProfileUpdateExample> call, Response<ProfileUpdateExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    profileUpdateHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            profileUpdateHandler.onError(message);
                        } else {
                            profileUpdateHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdateExample> call, Throwable t) {
                profileUpdateHandler.onError(t.getMessage());
            }
        });
    }

    //Near Me Hotels API
    public void nearMeMethod(int pageNo, int pageSize, final GetHotelsHandler getHotelsHandler) {
        api.getHotelsAPI(pageNo, pageSize).enqueue(new Callback<GetMeHotelsExample>() {
            @Override
            public void onResponse(Call<GetMeHotelsExample> call, Response<GetMeHotelsExample> response) {
                accessToken = response.headers().get("x-access-token");
                if (response.code() == 200) {
                    getHotelsHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getHotelsHandler.onError(message);
                        } else {
                            getHotelsHandler.onError(error);
                            //here error will show
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetMeHotelsExample> call, Throwable t) {
                getHotelsHandler.onError(t.getMessage());
            }
        });
    }

    //search By Name API
    public void searchMethod(String token, String name, final SearchHandler searchHandler) {
        api.searchByNameAPI(token, name)
                .enqueue(new Callback<SearchExample>() {
                    @Override
                    public void onResponse(Call<SearchExample> call, Response<SearchExample> response) {
                        accessToken = response.headers().get("x-access-token");
                        if (response.code() == 200) {
                            searchHandler.onSuccess(response.body(), accessToken);
                        } else {
                            String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                            try {
                                JSONObject jsonObject = new JSONObject(responceData);
                                String message = jsonObject.optString("message");
                                String error = jsonObject.optString("error");
                                if (!(message.equalsIgnoreCase(""))) {
                                    searchHandler.onError(message);
                                } else {
                                    searchHandler.onError(error);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchExample> call, Throwable t) {
                        searchHandler.onError(t.getMessage());
                    }
                });
    }


    //Hotels filter API
    public void filterMethod(int rating, int priceFrom, int priceTo, String paymentMode, boolean isFreeCancellation,
                             boolean isMealAvailable, boolean isParkingAvailable, boolean isPetAllow,
                             double latitude, double longitude, int nearBydistance, final FilterHandler filterHandler) {
        api.filterAPI(rating, priceFrom, priceTo, paymentMode, isFreeCancellation, isMealAvailable, isParkingAvailable, isPetAllow, latitude, longitude, nearBydistance)
                .enqueue(new Callback<FilterExample>() {
                    @Override
                    public void onResponse(Call<FilterExample> call, Response<FilterExample> response) {
                        accessToken = response.headers().get("x-access-token");
                        if (response.code() == 200) {
                            filterHandler.onSuccess(response.body(), accessToken);
                        } else {
                            String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                            try {
                                JSONObject jsonObject = new JSONObject(responceData);
                                String message = jsonObject.optString("message");
                                String error = jsonObject.optString("error");
                                if (!(message.equalsIgnoreCase(""))) {
                                    filterHandler.onError(message);
                                } else {
                                    filterHandler.onError(error);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FilterExample> call, Throwable t) {
                        filterHandler.onError(t.getMessage());
                    }
                });
    }


    //hotel View Method
    public void hotelViewMethod(String id, final HotelViewHandler hotelViewHandler) {
        api.hotelViewAPI(id)
                .enqueue(new Callback<HotelViewExample>() {
                    @Override
                    public void onResponse(Call<HotelViewExample> call, Response<HotelViewExample> response) {
                        accessToken = response.headers().get("x-access-token");
                        if (response.code() == 200) {
                            hotelViewHandler.onSuccess(response.body(), accessToken);
                        } else {
                            String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                            try {
                                JSONObject jsonObject = new JSONObject(responceData);
                                String message = jsonObject.optString("message");
                                String error = jsonObject.optString("error");
                                if (!(message.equalsIgnoreCase(""))) {
                                    hotelViewHandler.onError(message);
                                } else {
                                    hotelViewHandler.onError(error);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HotelViewExample> call, Throwable t) {
                        hotelViewHandler.onError(t.getMessage());
                    }
                });
    }


    public void changePasswordMethod(String token, String id, String oldPassword, String newPassword, final ChangePasswordHandler changePasswordHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("oldPassword", oldPassword);
        jsonObject.addProperty("newPassword", newPassword);
        api.changePasswordAPI(token, id, jsonObject)
                .enqueue(new Callback<ChangePasswordExample>() {
                    @Override
                    public void onResponse(Call<ChangePasswordExample> call, Response<ChangePasswordExample> response) {
                        accessToken = response.headers().get("x-access-token");
                        if (response.code() == 200) {
                            changePasswordHandler.onSuccess(response.body(), accessToken);
                        } else {
                            String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                            try {
                                JSONObject jsonObject = new JSONObject(responceData);
                                String message = jsonObject.optString("message");
                                String error = jsonObject.optString("error");
                                if (!(message.equalsIgnoreCase(""))) {
                                    changePasswordHandler.onError(message);
                                } else {
                                    changePasswordHandler.onError(error);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePasswordExample> call, Throwable t) {
                        changePasswordHandler.onError(t.getMessage());
                    }
                });
    }

    // Forgot Password API
    public void forgotPasswordMethod(String email, final ForgotPasswordHandler forgotPasswordHandler) {
        api.forgotPasswordAPI(email)
                .enqueue(new Callback<ForgotPasswordExample>() {

                    @Override
                    public void onResponse(Call<ForgotPasswordExample> call, Response<ForgotPasswordExample> response) {
                        if (response.code() == 200) {
                            forgotPasswordHandler.onSuccess(response.body());
                        } else {
                            String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                            try {
                                JSONObject jsonObject = new JSONObject(responceData);
                                String message = jsonObject.optString("message");
                                String error = jsonObject.optString("error");
                                if (!(message.equalsIgnoreCase(""))) {
                                    forgotPasswordHandler.onError(message);
                                } else {
                                    forgotPasswordHandler.onError(error);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPasswordExample> call, Throwable t) {
                        forgotPasswordHandler.onError(t.getMessage());
                    }
                });
    }

    // Otp Verify API
    public void otpVerifyMethod(String otp, String otpToken, final OtpVerifyHandler otpVerifyHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("otp", otp);
        jsonObject.addProperty("otpToken", otpToken);
        api.otpVerifyAPI(jsonObject).enqueue(new Callback<OtpVerifyExample>() {
            @Override
            public void onResponse(Call<OtpVerifyExample> call, Response<OtpVerifyExample> response) {
                if (response.code() == 200) {
                    otpVerifyHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            otpVerifyHandler.onError(message);
                        } else {
                            otpVerifyHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<OtpVerifyExample> call, Throwable t) {
                otpVerifyHandler.onError(t.getMessage());
            }
        });
    }

    // Reset Password API
    public void resetPasswordMethod(String otpToken, String email, String newPassword, final ResetPasswordHandler resetPasswordHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("otpToken", otpToken);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("newPassword", newPassword);
        api.resetPasswordAPI(jsonObject).enqueue(new Callback<ResetPasswordExample>() {
            @Override
            public void onResponse(Call<ResetPasswordExample> call, Response<ResetPasswordExample> response) {
                if (response.code() == 200) {
                    resetPasswordHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            resetPasswordHandler.onError(message);
                        } else {
                            resetPasswordHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordExample> call, Throwable t) {
                resetPasswordHandler.onError(t.getMessage());
            }
        });
    }

    // Near Me Hotels API
    public void nearMeHotelsMethod(String lng, String lat, final NearMeHotelsHandler nearMeHotelsHandler) {
        api.nearMeHotelsAPI(lng, lat).enqueue(new Callback<NearMeHotelsExample>() {
            @Override
            public void onResponse(Call<NearMeHotelsExample> call, Response<NearMeHotelsExample> response) {
                if (response.code() == 200) {
                    nearMeHotelsHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            nearMeHotelsHandler.onError(message);
                        } else {
                            nearMeHotelsHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NearMeHotelsExample> call, Throwable t) {
                nearMeHotelsHandler.onError(t.getMessage());
            }
        });
    }

    // Booking API
    public void bookingMethod(String name, String email, String phone, String primaryGuest,
                              String travelPurpose, boolean saveuserinfo, String checkIn, String checkOut,
                              int rooms, int adults, String userId, String hotelId, String stripeToken, int amount,
                              final BookingHandler bookingHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("primaryGuest", primaryGuest);
        jsonObject.addProperty("travelPurpose", travelPurpose);
        jsonObject.addProperty("saveuserinfo", saveuserinfo);
        jsonObject.addProperty("checkIn", checkIn);
        jsonObject.addProperty("checkOut", checkOut);
        jsonObject.addProperty("rooms", rooms);
        jsonObject.addProperty("adults", adults);
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("hotel", hotelId);
        jsonObject.addProperty("stripeToken", stripeToken);
        jsonObject.addProperty("amount", amount);
        api.bookingAPI(jsonObject).enqueue(new Callback<BookingHotelExample>() {
            @Override
            public void onResponse(Call<BookingHotelExample> call, Response<BookingHotelExample> response) {
                if (response.code() == 200) {
                    bookingHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            bookingHandler.onError(message);
                        } else {
                            bookingHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingHotelExample> call, Throwable t) {
                bookingHandler.onError(t.getMessage());
            }
        });
    }

    // Add To Favorite API
    public void addToFavoriteMethod(String userId, String hotelId, boolean isFav, final AddToFavoriteHandler addToFavoriteHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("hotelId", hotelId);
        jsonObject.addProperty("isFav", isFav);
        api.addToFavoriteAPI(jsonObject).enqueue(new Callback<AddToFavoriteExample>() {
            @Override
            public void onResponse(Call<AddToFavoriteExample> call, Response<AddToFavoriteExample> response) {
                if (response.code() == 200) {
                    addToFavoriteHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            addToFavoriteHandler.onError(message);
                        } else {
                            addToFavoriteHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddToFavoriteExample> call, Throwable t) {
                addToFavoriteHandler.onError(t.getMessage());
            }
        });
    }

    // Add To Favorite API
    public void getFavoriteMethod(String userId, final GetFavoriteHandler getFavoriteHandler) {
        api.getFavAPI(userId).enqueue(new Callback<GetFavoriteExample>() {
            @Override
            public void onResponse(Call<GetFavoriteExample> call, Response<GetFavoriteExample> response) {
                if (response.code() == 200) {
                    getFavoriteHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getFavoriteHandler.onError(message);
                        } else {
                            getFavoriteHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetFavoriteExample> call, Throwable t) {
                getFavoriteHandler.onError(t.getMessage());
            }
        });
    }

    // Get Latest Search API
    public void latestSearchMethod(String userId, final LatestSearchHandler latestSearchHandler) {
        api.latestSearchAPI(userId).enqueue(new Callback<LatestSearchExample>() {
            @Override
            public void onResponse(Call<LatestSearchExample> call, Response<LatestSearchExample> response) {
                if (response.code() == 200) {
                    latestSearchHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            latestSearchHandler.onError(message);
                        } else {
                            latestSearchHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LatestSearchExample> call, Throwable t) {
                latestSearchHandler.onError(t.getMessage());
            }
        });
    }

    // Get Upcoming Bookings API
    public void upcomingBookingsMethod(String userId, final UpcomingBookingsHandler upcomingBookingsHandler) {
        api.upcomingBookingsAPI(userId).enqueue(new Callback<UpcomingBookingsExample>() {
            @Override
            public void onResponse(Call<UpcomingBookingsExample> call, Response<UpcomingBookingsExample> response) {
                if (response.code() == 200) {
                    upcomingBookingsHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            upcomingBookingsHandler.onError(message);
                        } else {
                            upcomingBookingsHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpcomingBookingsExample> call, Throwable t) {
                upcomingBookingsHandler.onError(t.getMessage());
            }
        });
    }

    // Change Booking API
    public void changeBookingMethod(String bookingId, String checkIn, String checkOut,
                                    int rooms, int adults, final ChangeBookingHandler changeBookingHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("checkIn", checkIn);
        jsonObject.addProperty("checkOut", checkOut);
        jsonObject.addProperty("rooms", rooms);
        jsonObject.addProperty("adults", adults);
        api.changeBookingAPI(bookingId, jsonObject).enqueue(new Callback<ChangeBookingExample>() {
            @Override
            public void onResponse(Call<ChangeBookingExample> call, Response<ChangeBookingExample> response) {
                if (response.code() == 200) {
                    changeBookingHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            changeBookingHandler.onError(message);
                        } else {
                            changeBookingHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangeBookingExample> call, Throwable t) {
                changeBookingHandler.onError(t.getMessage());
            }
        });
    }

    // Cancel Booking API
    public void cancelBookingMethod(String bookingId, final CancelBookingHandler cancelBookingHandler) {
        api.cancelBookingAPI(bookingId).enqueue(new Callback<CancelBookingExample>() {
            @Override
            public void onResponse(Call<CancelBookingExample> call, Response<CancelBookingExample> response) {
                if (response.code() == 200) {
                    cancelBookingHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            cancelBookingHandler.onError(message);
                        } else {
                            cancelBookingHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CancelBookingExample> call, Throwable t) {
                cancelBookingHandler.onError(t.getMessage());
            }
        });
    }

    // Hotel Sort By method
    public void hotelSortByMethod(final HotelSortByHandler hotelSortByHandler) {
        api.hotelSortByAPI().enqueue(new Callback<HotelSortByExample>() {
            @Override
            public void onResponse(Call<HotelSortByExample> call, Response<HotelSortByExample> response) {
                if (response.code() == 200) {
                    hotelSortByHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            hotelSortByHandler.onError(message);
                        } else {
                            hotelSortByHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HotelSortByExample> call, Throwable t) {
                hotelSortByHandler.onError(t.getMessage());
            }
        });
    }

    // Get Ratings By Hotel id method
    public void getRatingsMethod(String hotelId, int pageNo, int pageSize, final GetRatingsHandler getRatingsHandler) {
        api.getRatingsAPI(hotelId, pageNo, pageSize).enqueue(new Callback<GetRatingsExample>() {
            @Override
            public void onResponse(Call<GetRatingsExample> call, Response<GetRatingsExample> response) {
                if (response.code() == 200) {
                    getRatingsHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getRatingsHandler.onError(message);
                        } else {
                            getRatingsHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRatingsExample> call, Throwable t) {
                getRatingsHandler.onError(t.getMessage());
            }
        });
    }

    // Get Finished Bookings method
    public void finishedBookingMethod(String userId, final FinishedBookingHandler finishedBookingHandler) {
        api.finishedBookingAPI(userId).enqueue(new Callback<FinishedBookingExample>() {
            @Override
            public void onResponse(Call<FinishedBookingExample> call, Response<FinishedBookingExample> response) {
                if (response.code() == 200) {
                    finishedBookingHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            finishedBookingHandler.onError(message);
                        } else {
                            finishedBookingHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<FinishedBookingExample> call, Throwable t) {
                finishedBookingHandler.onError(t.getMessage());
            }
        });
    }


    // Write Review method
    public void writeReviewMethod(String userId, String hotelId, int rating, String review, final WriteReviewHandler writeReviewHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("hotelId", hotelId);
        jsonObject.addProperty("rating", rating);
        jsonObject.addProperty("review", review);
        api.writeReviewAPI(jsonObject).enqueue(new Callback<WriteReviewExample>() {
            @Override
            public void onResponse(Call<WriteReviewExample> call, Response<WriteReviewExample> response) {
                if (response.code() == 200) {
                    writeReviewHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            writeReviewHandler.onError(message);
                        } else {
                            writeReviewHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<WriteReviewExample> call, Throwable t) {
                writeReviewHandler.onError(t.getMessage());
            }
        });
    }

    // Search By Parameters Method
    public void searchByParametersMethod(String city, String checkIn, String checkOut, String adults, String rooms, final SearchByParamtersHandler SearchByParamtersHandler) {
        api.searchByParametersAPI(city, checkIn, checkOut, adults, rooms).enqueue(new Callback<SearchByParametersExample>() {
            @Override
            public void onResponse(Call<SearchByParametersExample> call, Response<SearchByParametersExample> response) {
                if (response.code() == 200) {
                    SearchByParamtersHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            SearchByParamtersHandler.onError(message);
                        } else {
                            SearchByParamtersHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchByParametersExample> call, Throwable t) {
                SearchByParamtersHandler.onError(t.getMessage());
            }
        });
    }
}