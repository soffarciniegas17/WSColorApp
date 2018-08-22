package com.worldsills.wscolorapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class RedesSociales {

    Context context;
    public RedesSociales(Context context){

        this.context= context;
    }

    public void compartir (int puntaje){
        FacebookSdk.sdkInitialize(context);
        ShareDialog shareDialog= new ShareDialog((Activity) context);
        if(shareDialog.canShow(ShareLinkContent.class)){
            ShareLinkContent content= new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://developers.facebook.com"))
                    .setQuote("Jugue ColorApp, mi puntuación fue "+ puntaje+ "Esta app fue creada con Facebook developers")
                    .build();

            shareDialog.show(content);
        }

    }
}
