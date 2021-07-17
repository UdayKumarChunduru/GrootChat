// Generated by view binder compiler. Do not edit!
package com.v.grootchat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.v.grootchat.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySettingsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextInputEditText EnterUserName;

  @NonNull
  public final TextInputEditText EnterUserStatus;

  @NonNull
  public final LinearLayout LL1;

  @NonNull
  public final LinearLayout LL2;

  @NonNull
  public final LinearLayout LL3;

  @NonNull
  public final LinearLayout LL4;

  @NonNull
  public final RelativeLayout RL1;

  @NonNull
  public final AppCompatButton SaveButton;

  @NonNull
  public final TextInputLayout TIL1;

  @NonNull
  public final TextInputLayout TIL2;

  @NonNull
  public final ImageView profileImage;

  @NonNull
  public final TextView tv1;

  @NonNull
  public final TextView tv2;

  @NonNull
  public final TextView tv3;

  @NonNull
  public final TextView tv4;

  @NonNull
  public final TextView tv6;

  @NonNull
  public final ImageView uploadImage;

  private ActivitySettingsBinding(@NonNull RelativeLayout rootView,
      @NonNull TextInputEditText EnterUserName, @NonNull TextInputEditText EnterUserStatus,
      @NonNull LinearLayout LL1, @NonNull LinearLayout LL2, @NonNull LinearLayout LL3,
      @NonNull LinearLayout LL4, @NonNull RelativeLayout RL1, @NonNull AppCompatButton SaveButton,
      @NonNull TextInputLayout TIL1, @NonNull TextInputLayout TIL2, @NonNull ImageView profileImage,
      @NonNull TextView tv1, @NonNull TextView tv2, @NonNull TextView tv3, @NonNull TextView tv4,
      @NonNull TextView tv6, @NonNull ImageView uploadImage) {
    this.rootView = rootView;
    this.EnterUserName = EnterUserName;
    this.EnterUserStatus = EnterUserStatus;
    this.LL1 = LL1;
    this.LL2 = LL2;
    this.LL3 = LL3;
    this.LL4 = LL4;
    this.RL1 = RL1;
    this.SaveButton = SaveButton;
    this.TIL1 = TIL1;
    this.TIL2 = TIL2;
    this.profileImage = profileImage;
    this.tv1 = tv1;
    this.tv2 = tv2;
    this.tv3 = tv3;
    this.tv4 = tv4;
    this.tv6 = tv6;
    this.uploadImage = uploadImage;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.EnterUserName;
      TextInputEditText EnterUserName = rootView.findViewById(id);
      if (EnterUserName == null) {
        break missingId;
      }

      id = R.id.EnterUserStatus;
      TextInputEditText EnterUserStatus = rootView.findViewById(id);
      if (EnterUserStatus == null) {
        break missingId;
      }

      id = R.id.LL1;
      LinearLayout LL1 = rootView.findViewById(id);
      if (LL1 == null) {
        break missingId;
      }

      id = R.id.LL2;
      LinearLayout LL2 = rootView.findViewById(id);
      if (LL2 == null) {
        break missingId;
      }

      id = R.id.LL3;
      LinearLayout LL3 = rootView.findViewById(id);
      if (LL3 == null) {
        break missingId;
      }

      id = R.id.LL4;
      LinearLayout LL4 = rootView.findViewById(id);
      if (LL4 == null) {
        break missingId;
      }

      RelativeLayout RL1 = (RelativeLayout) rootView;

      id = R.id.SaveButton;
      AppCompatButton SaveButton = rootView.findViewById(id);
      if (SaveButton == null) {
        break missingId;
      }

      id = R.id.TIL1;
      TextInputLayout TIL1 = rootView.findViewById(id);
      if (TIL1 == null) {
        break missingId;
      }

      id = R.id.TIL2;
      TextInputLayout TIL2 = rootView.findViewById(id);
      if (TIL2 == null) {
        break missingId;
      }

      id = R.id.profileImage;
      ImageView profileImage = rootView.findViewById(id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.tv1;
      TextView tv1 = rootView.findViewById(id);
      if (tv1 == null) {
        break missingId;
      }

      id = R.id.tv2;
      TextView tv2 = rootView.findViewById(id);
      if (tv2 == null) {
        break missingId;
      }

      id = R.id.tv3;
      TextView tv3 = rootView.findViewById(id);
      if (tv3 == null) {
        break missingId;
      }

      id = R.id.tv4;
      TextView tv4 = rootView.findViewById(id);
      if (tv4 == null) {
        break missingId;
      }

      id = R.id.tv6;
      TextView tv6 = rootView.findViewById(id);
      if (tv6 == null) {
        break missingId;
      }

      id = R.id.uploadImage;
      ImageView uploadImage = rootView.findViewById(id);
      if (uploadImage == null) {
        break missingId;
      }

      return new ActivitySettingsBinding((RelativeLayout) rootView, EnterUserName, EnterUserStatus,
          LL1, LL2, LL3, LL4, RL1, SaveButton, TIL1, TIL2, profileImage, tv1, tv2, tv3, tv4, tv6,
          uploadImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
