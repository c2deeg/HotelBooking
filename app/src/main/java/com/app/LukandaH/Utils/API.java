package com.app.LukandaH.Utils;

import com.app.LukandaH.Models.AddToFavoritesModel.AddToFavoriteExample;
import com.app.LukandaH.Models.BookingModels.BookingHotelExample;
import com.app.LukandaH.Models.CancelBookingModels.CancelBookingExample;
import com.app.LukandaH.Models.ChangeBookingModels.ChangeBookingExample;
import com.app.LukandaH.Models.ChangePasswordModels.ChangePasswordExample;
import com.app.LukandaH.Models.FillBookingInfoModels.FillBookingInfoExample;
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

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //Login
    @POST("users/login")
    Call<LoginExample> loginAPI(@Body JsonObject jsonObject);

    //Sign Up
    @POST("users/create")
    Call<SignUpExample> signUpAPI(@Body JsonObject jsonObject);

    //User Profile Data
    @GET("users/profile/{id}")
    Call<ProfileExample> profileAPI(@Header("x-access-token") String token, @Path("id") String id);

    //Profile Image Upload
    @Multipart
    @PUT("users/profileImageUpload/{id}")
    Call<ProfileImageExample> profileImageAPI(@Part MultipartBody.Part file, @Path("id") String id);

    // update profile API (Pending)
    @PUT("users/updateprofile/{id}")
    Call<ProfileUpdateExample> profileUpdateAPI(@Path("id") String id, @Body JsonObject jsonObject);

    // Get NearBy Hotels API
    @GET("hotels/getHotels")
    Call<GetMeHotelsExample> getHotelsAPI(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("hotels/byFilter")
    Call<FilterExample> filterAPI(@Query("rating") int rating, @Query("priceFrom") int priceFrom,
                                  @Query("priceTo") int priceTo, @Query("paymentMode") String paymentMode,
                                  @Query("isFreeCancellation") boolean isFreeCancellation, @Query("isMealAvailable") boolean isMealAvailable,
                                  @Query("isParkingAvailable") boolean isParkingAvailable, @Query("isPetAllow") boolean isPetAllow,
                                  @Query("lat") double latitude, @Query("long") double longitude,
                                  @Query("distance") int nearBydistance);


    //Search By name API
    @GET("hotels/search")
    Call<SearchExample> searchByNameAPI(@Header("x-access-token") String token, @Query("name") String name);

    //hotel view API
    @GET("hotels/getHotelById/{id}")
    Call<HotelViewExample> hotelViewAPI(@Path("id") String id);

    // Change Password API
    @PUT("users/resetPassword/{id}")
    Call<ChangePasswordExample> changePasswordAPI(@Header("x-access-token") String token, @Path("id") String id, @Body JsonObject jsonObject);

    // Social Login API
    @POST("users/socialLogin")
    Call<SocialLoginExample> socialLoginAPI(@Body JsonObject jsonObject);

    // Forgot Passowrd API
    @GET("users/otp")
    Call<ForgotPasswordExample> forgotPasswordAPI(@Query("email") String email);

    // Otp Verify API
    @POST("users/otpVerify")
    Call<OtpVerifyExample> otpVerifyAPI(@Body JsonObject jsonObject);

    // Reset Password API
    @POST("users/forgotPassword")
    Call<ResetPasswordExample> resetPasswordAPI(@Body JsonObject jsonObject);

    // Near Me Hotels API
    @GET("hotels/searchByLocation")
    Call<NearMeHotelsExample> nearMeHotelsAPI(@Query("lng") String longitude, @Query("lat") String latitude);

    // Booking API
    @POST("bookings/book")
    Call<BookingHotelExample> bookingAPI(@Body JsonObject jsonObject);

    // favorites API
    @POST("hotels/favorite")
    Call<AddToFavoriteExample> addToFavoriteAPI (@Body JsonObject jsonObject);

    // Get Favorites API
    @GET("hotels/getFav/{userId}")
    Call<GetFavoriteExample> getFavAPI (@Path("userId") String userId);

    // Get Latest Searched Hotels API
    @GET("hotels/latestSearched/{userId}")
    Call<LatestSearchExample> latestSearchAPI (@Path("userId") String userId);

    // Get Upcoming Bookings API
    @GET("bookings/upcomingBooking/{userId}")
    Call<UpcomingBookingsExample> upcomingBookingsAPI (@Path("userId") String userId);

    // Change Booking Information API
    @PUT("bookings/changeBooking/{id}")
    Call<ChangeBookingExample> changeBookingAPI (@Path("id") String bookingId,
                                                 @Body JsonObject jsonObject);
    // Cancel Booking API
    @PUT("bookings/cancelBooking/{bookingId}")
    Call<CancelBookingExample> cancelBookingAPI(@Path("bookingId") String bookingId);

    // Hotel Sort by API
    @GET("hotels/hotelsSortBy")
    Call<HotelSortByExample> hotelSortByAPI();

    // Get ratings By Hotel id API
    @GET("rating/byHotelId")
    Call<GetRatingsExample> getRatingsAPI (@Query("hotelId") String hotelId, @Query("pageNo") int pageNo,
                                           @Query("pageSize") int pageSize);

    // Get Fininshed Bookings API
    @GET("bookings/finishedbookinguserById/{userId}")
    Call<FinishedBookingExample> finishedBookingAPI (@Path("userId") String userId);

    //Write Review API
    @POST("rating/add")
    Call<WriteReviewExample> writeReviewAPI (@Body JsonObject jsonObject);

    // Search By Parameters API
    @GET("hotels/searchByCity")
    Call<SearchByParametersExample> searchByParametersAPI (@Query("city") String city, @Query("checkIn") String checkIn,
                                                           @Query("checkOut") String checkOut,@Query("adults") String adults,
                                                           @Query("rooms") String rooms);
}