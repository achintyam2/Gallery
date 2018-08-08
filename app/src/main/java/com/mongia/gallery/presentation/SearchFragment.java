package com.mongia.gallery.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.mongia.gallery.Gallery;
import com.mongia.gallery.R;
import com.mongia.gallery.model.Photo;
import com.mongia.gallery.model.Pictures;
import com.mongia.gallery.util.Injection;
import com.mongia.gallery.util.ProgressDialog;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements Contract.SearchView{

    private static SearchFragment searchFragment;
    private RecyclerView searchRecycler;
    private EditText input;
    private FragmentHandler fragmentHandler;
    private Contract.SearchPresenter searchPresenter;
    private ProgressDialog progressDialog;
    private PictureAdapter pictureAdapter;

    public SearchFragment() {}

    public static SearchFragment getInstance(){
        if (searchFragment == null)
            searchFragment = new SearchFragment();
        return searchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHandler = (FragmentHandler) getActivity();
        if (fragmentHandler != null) {
            fragmentHandler.setTitle("Search");
        }
        searchPresenter = new SearchPresenter(Injection.provideRepository(Gallery.context),this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view != null){
            searchRecycler = view.findViewById(R.id.recycler);
            pictureAdapter = new PictureAdapter(new ArrayList<Photo>(0));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            searchRecycler.setLayoutManager(mLayoutManager);
            searchRecycler.setAdapter(pictureAdapter);
            input = view.findViewById(R.id.query);
            input.requestFocus();
            input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH){
                        searchPresenter.getSearchedPics(input.getText().toString().trim());
                        progressDialog = new ProgressDialog(getContext());
                        progressDialog.show();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onResponse(Pictures pictures) {
        progressDialog.cancel();
        if ("ok".equalsIgnoreCase(pictures.getStatus())) {
            pictureAdapter.addData(pictures.getPhotos().getPhotoList());
        }
    }

    @Override
    public void onErrorResponse(Throwable throwable) {

    }
}
