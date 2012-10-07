package info.alfaridi.hybridapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int CAMERA_PIC_REQUEST = 1337;
	private WebView webView;
	private Button btnCamera, btnToast;
	private Context context;
	private Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		activity = this;

		// Initialization
		btnCamera = (Button) findViewById(R.id.btnCamera);
		webView = (WebView) findViewById(R.id.webView);
		btnToast = (Button) findViewById(R.id.btnToast);

		// Bind action
		btnToast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "This call from native", Toast.LENGTH_LONG).show();
			}
		});

		btnCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getPackageManager().hasSystemFeature(
						PackageManager.FEATURE_CAMERA)) {
					
					Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
					
				} else {
					Toast.makeText(context, "No Camera", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		// Webview setting
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JavascriptInterface(context, activity), "Android");
		webView.loadUrl("file:///android_asset/www/dashboard.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAMERA_PIC_REQUEST) {
	    	Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
	    	ImageView image = (ImageView) findViewById(R.id.imageView1);  
	    	image.setImageBitmap(thumbnail);
	    }
	}


}
