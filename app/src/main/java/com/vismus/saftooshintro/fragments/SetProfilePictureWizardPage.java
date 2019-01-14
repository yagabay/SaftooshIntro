package com.vismus.saftooshintro.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.WizardView.WizardPage;

import java.io.FileNotFoundException;

public class SetProfilePictureWizardPage extends WizardPage{

    public static final int PICK_IMAGE = 1;

    ImageView _imvProfilePicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.set_profile_picture_wizard_page, container, false);
        _imvProfilePicture = rootView.findViewById(R.id.imv_profile_picture);
        _imvProfilePicture.setOnClickListener(new OnUserPictureImageViewClickListener());
        return rootView;
    }

    class OnUserPictureImageViewClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null) {
            return;
        }
        if (requestCode == PICK_IMAGE) {
            Bitmap picture = null;
            try {
                picture = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(data.getData()));
            } catch (FileNotFoundException e) {
                Toast.makeText(getContext(), "Error: failed to decode selected file", Toast.LENGTH_SHORT).show();
            }
            DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
            Bitmap pictureThumb = ThumbnailUtils.extractThumbnail(picture, displayMetrics.widthPixels, displayMetrics.widthPixels);
            _imvProfilePicture.setImageBitmap(pictureThumb);
        }
    }

}
