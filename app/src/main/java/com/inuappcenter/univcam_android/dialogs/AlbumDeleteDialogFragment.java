package com.inuappcenter.univcam_android.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inuappcenter.univcam_android.R;
import com.inuappcenter.univcam_android.database.RealmHelper;

import io.realm.Realm;


/**
 * Created by ichaeeun on 2017. 7. 24..
 */

public class AlbumDeleteDialogFragment extends DialogFragment {

    TextView deleteTextButton;
    TextView albumNameTextView;
    TextView cancelTextButton;
    ImageButton cancelImgButton;
    AlbumDeleteInterface mAlbumDialogInterface;
    Realm mRealm;
    RealmHelper mRealmHelper;

    public static AlbumDeleteDialogFragment newInstance(AlbumDeleteInterface albumDialogInterface, String albumName) {
        AlbumDeleteDialogFragment fragment = new AlbumDeleteDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("albumName", albumName);
        fragment.setArguments(bundle);
        fragment.mAlbumDialogInterface = albumDialogInterface;
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_album, container);

        Realm.init(getContext());
        mRealm = Realm.getDefaultInstance();

        mRealmHelper = new RealmHelper(mRealm);
//        mRealmHelper.updateAlbumSorted();

       final String albumName = getArguments().getString("albumName");



        deleteTextButton = view.findViewById(R.id.deleteButton);
        albumNameTextView = view.findViewById(R.id.albumName);
        cancelTextButton = view.findViewById(R.id.cancelButton);
        cancelImgButton = view.findViewById(R.id.cancel_button);


        albumNameTextView.setText(albumName);

        deleteTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlbumDialogInterface.deleteAlbum(albumName);
                dismiss();
            }
        });

        cancelImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        cancelTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    return view;

    }
    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }
}
