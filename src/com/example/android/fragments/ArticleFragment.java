/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ArticleFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    WebView viewer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        
       
       
         
        
        View view = inflater.inflate(R.layout.web_view, container, false);
        viewer = (WebView) view.findViewById(R.id.tutView);
        viewer.getSettings().setJavaScriptEnabled(true);
        viewer.setWebViewClient(new SwAWebClient());
        viewer.loadUrl("http://desiq.org/schedule-july-3-2013");
        
        return  view;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.article_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        /*TextView article = (TextView) getActivity().findViewById(R.id.article);
        article.setText(Ipsum.Articles[position]);
        mCurrentPosition = position;
        */
        //setContentView(R.layout.web_view);
      //  Intent launchingIntent = getIntent();
    	String content = "http://desiq.org/schedule";
        if (position == 0)
        	content =  "http://desiq.org/schedule-july-3-2013";
        if (position == 1)
        	content =  "http://desiq.org/schedule-july-4-2013";
        if (position == 2)
        	content =  "http://desiq.org/schedule-july-5-2013";
        if (position == 3)
        	content =  "http://desiq.org/schedule-july-6-2013";
        	
        viewer.getSettings().setJavaScriptEnabled(true);
        viewer.setWebViewClient(new SwAWebClient());
        viewer.loadUrl(content);  
        
        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
    
    private class SwAWebClient extends WebViewClient {
    	 
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
         
    }
}