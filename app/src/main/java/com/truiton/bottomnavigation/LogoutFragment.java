/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.truiton.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class LogoutFragment extends Fragment {


    public static LogoutFragment newInstance() {
        LogoutFragment fragment = new LogoutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        getContext().getTheme().applyStyle(R.style.AppTheme, true);

        RelativeLayout mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_logout, container, false);

        // note that we're looking for a button with id="@+id/button" in your inflated layout
        // Naturally, this can be any View; it doesn't have to be a button
        Button mButton = (Button) mRelativeLayout.findViewById(R.id.logout);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button,
                // e.g. launch a new activity

                // Launching the login activity
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
               // Toast.makeText(getActivity(), "Mission Successful", Toast.LENGTH_SHORT).show();
            }
        });

        // after you've done all your manipulation, return your layout to be shown
        return mRelativeLayout;
    }

}
