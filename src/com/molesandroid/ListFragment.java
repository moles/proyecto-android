package com.molesandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class ListFragment extends Fragment implements OnClickListener {

	private EditText editDescription;
	private final int REQUEST_GALLERY = 100;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.main_list, null);

		view.findViewById(R.id.buttonGalery).setOnClickListener(this);
		view.findViewById(R.id.buttonCamera).setOnClickListener(this);

		editDescription = (EditText) view.findViewById(R.id.editDescription);

		return view;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonGalery:
			// metodo para llamar a camara
			Toast.makeText(
					getActivity(),
					String.format(
							"Aqui deberia de abrir la galeria de fotos!, Descripcion : %s",
							editDescription.getText()), Toast.LENGTH_LONG)
					.show();

			Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
			galleryIntent.setType("image/*");
			startActivityForResult(galleryIntent, REQUEST_GALLERY);
			break;
		case R.id.buttonCamera:
			Toast.makeText(
					getActivity(),
					String.format(
							"Aqui deberia de abrir la camara!, Descripcion : %s",
							editDescription.getText()), Toast.LENGTH_LONG)
					.show();
			Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, REQUEST_GALLERY);
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case REQUEST_GALLERY:
				Toast.makeText(getActivity(), "Gallery called Ok",
						Toast.LENGTH_LONG).show();
				break;
			}
		} else {
			Toast.makeText(getActivity(), "Result not ok", Toast.LENGTH_LONG)
					.show();
		}
	}
}
