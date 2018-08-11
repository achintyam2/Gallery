package com.mongia.gallery.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mongia.gallery.Gallery;
import com.mongia.gallery.R;
import com.mongia.gallery.model.Photo;
import com.mongia.gallery.model.Pictures;
import com.mongia.gallery.util.Injection;
import com.mongia.gallery.util.ProgressDialog;

import java.util.ArrayList;

public class RecentFragment extends Fragment implements Contract.RecentView{

    private static RecentFragment recentFragment;
    private RecyclerView recentRecycler;
    private FragmentHandler fragmentHandler;
    private Contract.RecentPresenter recentPresenter;
    private ProgressDialog progressDialog;
    PictureAdapter pictureAdapter;

    public RecentFragment() {}

    public static RecentFragment getInstance(){
        return new RecentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentHandler = (FragmentHandler) getActivity();
        if (fragmentHandler != null) {
            fragmentHandler.setTitle("Recent Pictures");
        }
        recentPresenter = new RecentPresenter(Injection.provideRepository(Gallery.context),this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recent,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view != null){
            recentRecycler = view.findViewById(R.id.recyclerView);
            pictureAdapter = new PictureAdapter(new ArrayList<Photo>(0));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recentRecycler.setLayoutManager(mLayoutManager);
            recentRecycler.setAdapter(pictureAdapter);
            recentPresenter.getRecentPics();
        }
    }

    @Override
    public void onResponse(Pictures pictures) {
        progressDialog.cancel();
        if ("ok".equalsIgnoreCase(pictures.getStatus())) {
            pictureAdapter.addData(pictures.getPhotos().getPhotoList());
        }else{

        }
    }

    @Override
    public void onErrorResponse(Throwable throwable) {

    }

}
