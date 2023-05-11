package com.app.LukandaH.Activities.SearchActivity.Presenter;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.LukandaH.Activities.SearchActivity.View.SearchView;
import com.app.LukandaH.Adapters.RvSearchListAdapter;
import com.app.LukandaH.Handlers.FilterHandler;
import com.app.LukandaH.Handlers.GetHotelsHandler;
import com.app.LukandaH.Handlers.HotelSortByHandler;
import com.app.LukandaH.Handlers.SearchHandler;
import com.app.LukandaH.Models.GetHotelsModels.GetMeHotelsExample;
import com.app.LukandaH.Models.HotelsSortByModels.HotelSortByExample;
import com.app.LukandaH.Models.SearchFilterModels.FilterExample;
import com.app.LukandaH.Models.SearchModels.SearchExample;
import com.app.LukandaH.Utils.CSPreferences;
import com.app.LukandaH.Utils.Utils;
import com.app.LukandaH.Utils.WebServices;

public class SearchPresenter {
    private final SearchView searchView;
    private final Activity activity;
    private String token;
    private EditText name;
    private RecyclerView rv_searchResult;
    private RvSearchListAdapter nearMeAdapter;
    private int rating;
    private int priceFrom;
    private int priceTo;
    private String paymentMode;
    private boolean isFreeCancellation;
    private boolean isMealAvailable;
    private boolean isParkingAvailable;
    private boolean isPetAllow;
    private double latitude;
    private double longitude;
    private int nearByDistance;

    public SearchPresenter(Activity activity, SearchView searchView) {
        this.activity = activity;
        this.searchView = searchView;
    }

    public void searchMethod(EditText name, RecyclerView rv_searchResult) {
        this.name = name;
        this.rv_searchResult = rv_searchResult;
//        this.nearMeAdapter = nearMeAdapter;

        searchView.showDialog(activity);
        token = CSPreferences.readString(activity, Utils.LOGINTOKEN);
        WebServices.getmInstance().searchMethod(token, name.getText().toString().trim(), new SearchHandler() {
            @Override
            public void onSuccess(SearchExample searchExample, String accessToken) {
                GetMeHotelsExample getMeHotelsExample = new GetMeHotelsExample();
                FilterExample filterExample = new FilterExample();
                HotelSortByExample hotelSortByExample = new HotelSortByExample();
                searchView.hideDialog();
                if (searchExample != null) {
                    if (searchExample.getIsSuccess() == true) {
                        nearMeAdapter = new RvSearchListAdapter(activity, getMeHotelsExample.getData(), filterExample.getData(), searchExample.getData(), hotelSortByExample.getData());
                        rv_searchResult.setLayoutManager(new LinearLayoutManager(activity));
                        rv_searchResult.setAdapter(nearMeAdapter);
                    } else {
                        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity, "error2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message) {
                searchView.hideDialog();
                searchView.showMessage(activity, message);
            }
        });
    }

    private boolean checkValidation() {
        String search = name.getText().toString();

        if (search.isEmpty()) {
            Toast.makeText(activity, "Please enter hotel name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    public void getHotelsMethod(RecyclerView rv_searchResult) {
        this.rv_searchResult = rv_searchResult;
        FilterExample filterExample = new FilterExample();
        searchView.showDialog(activity);
        WebServices.getmInstance().nearMeMethod(1, 100, new GetHotelsHandler() {
            @Override
            public void onSuccess(GetMeHotelsExample getMeHotelsExample, String accessToken) {
                searchView.hideDialog();
                if (getMeHotelsExample != null) {
                    if (getMeHotelsExample.getIsSuccess() == true) {
                        searchView.showMessage(activity, getMeHotelsExample.getMessage());
                        SearchExample searchExample = new SearchExample();
                        HotelSortByExample hotelSortByExample = new HotelSortByExample();
                        nearMeAdapter = new RvSearchListAdapter(activity, getMeHotelsExample.getData(), filterExample.getData(), searchExample.getData(), hotelSortByExample.getData());
                        rv_searchResult.setLayoutManager(new LinearLayoutManager(activity));
                        rv_searchResult.setAdapter(nearMeAdapter);
                    } else {
                        searchView.showMessage(activity, getMeHotelsExample.getMessage());
                    }
                } else {
                    searchView.showMessage(activity, getMeHotelsExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                searchView.hideDialog();
                searchView.showMessage(activity, message);
            }
        });
    }

    public void filterMethod(int rating, int priceFrom, int priceTo, String paymentMode, boolean isFreeCancellation,
                             boolean isMealAvailable, boolean isParkingAvailable, boolean isPetAllow, double latitude,
                             double longitude, int nearByDistance, RecyclerView rv_searchResult) {
        this.rating = rating;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.paymentMode = paymentMode;
        this.isFreeCancellation = isFreeCancellation;
        this.isMealAvailable = isMealAvailable;
        this.isParkingAvailable = isParkingAvailable;
        this.isPetAllow = isPetAllow;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nearByDistance = nearByDistance;
        this.rv_searchResult = rv_searchResult;


        searchView.showDialog(activity);
        WebServices.getmInstance().filterMethod(rating, priceFrom, priceTo, paymentMode, isFreeCancellation, isMealAvailable, isParkingAvailable, isPetAllow, latitude,
                longitude, nearByDistance, new FilterHandler() {
                    @Override
                    public void onSuccess(FilterExample filterExample, String accessToken) {
                        GetMeHotelsExample nearMeHotelsExample = new GetMeHotelsExample();
                        SearchExample searchExample = new SearchExample();
                        HotelSortByExample hotelSortByExample = new HotelSortByExample();
                        searchView.hideDialog();
//                        Toast.makeText(activity, latitude+", "+ longitude, Toast.LENGTH_SHORT).show();
                        if (filterExample != null) {
                            searchView.showMessage(activity, filterExample.getMessage());
                            if (filterExample.getIsSuccess() == true) {
                                nearMeAdapter = new RvSearchListAdapter(activity, nearMeHotelsExample.getData(), filterExample.getData(), searchExample.getData(), hotelSortByExample.getData());
                                rv_searchResult.setLayoutManager(new LinearLayoutManager(activity));
                                rv_searchResult.setAdapter(nearMeAdapter);
                            } else {
                                searchView.showMessage(activity, filterExample.getMessage());
                            }
                        } else {
                            searchView.showMessage(activity, filterExample.getMessage());
                        }
                    }

                    @Override
                    public void onError(String message) {
                        searchView.hideDialog();
                        searchView.showMessage(activity, message);
                    }
                });
    }

    public void hotelSortByMethod(RecyclerView rv_searchResult) {
        SearchExample searchExample = new SearchExample();
        FilterExample filterExample = new FilterExample();
        this.rv_searchResult = rv_searchResult;
        searchView.showDialog(activity);
        WebServices.getmInstance().hotelSortByMethod(new HotelSortByHandler() {
            @Override
            public void onSuccess(HotelSortByExample hotelSortByExample) {
                searchView.hideDialog();
                GetMeHotelsExample nearMeHotelsExample = new GetMeHotelsExample();

                if (hotelSortByExample != null) {
                    if (hotelSortByExample.getIsSuccess() == true) {
                        searchView.showMessage(activity, hotelSortByExample.getMessage());
                        nearMeAdapter = new RvSearchListAdapter(activity, nearMeHotelsExample.getData(), filterExample.getData(), searchExample.getData(), hotelSortByExample.getData());
                        rv_searchResult.setLayoutManager(new LinearLayoutManager(activity));
                        rv_searchResult.setAdapter(nearMeAdapter);
                    } else {
                        searchView.showMessage(activity, hotelSortByExample.getMessage());
                    }
                } else {
                    searchView.showMessage(activity, hotelSortByExample.getMessage());
                }
            }

            @Override
            public void onError(String message) {
                searchView.hideDialog();
                searchView.showMessage(activity, message);
            }
        });
    }
}
