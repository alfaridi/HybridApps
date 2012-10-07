package info.alfaridi.hybridapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class JavascriptInterface implements Cloneable {

	private static final int CAMERA_PIC_REQUEST = 1337;
	private Context context;
	private Activity activity;
	
	public JavascriptInterface(Context context, Activity activity) {
		this.context = context;
		this.activity = activity;
	}
	
	public void toastMakeText() {
		Toast.makeText(context, "This call from HTML", Toast.LENGTH_LONG).show();
	}

	public void cameraTakePicture() {
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		activity.startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	}
	
}
