// Generated by view binder compiler. Do not edit!
package com.v.grootchat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import com.v.grootchat.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SampleReceiverBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout CL1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final Guideline guideline4;

  @NonNull
  public final TextView receiverText;

  @NonNull
  public final TextView receiverTime;

  private SampleReceiverBinding(@NonNull ConstraintLayout rootView, @NonNull ConstraintLayout CL1,
      @NonNull Guideline guideline2, @NonNull Guideline guideline3, @NonNull Guideline guideline4,
      @NonNull TextView receiverText, @NonNull TextView receiverTime) {
    this.rootView = rootView;
    this.CL1 = CL1;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.guideline4 = guideline4;
    this.receiverText = receiverText;
    this.receiverTime = receiverTime;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SampleReceiverBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SampleReceiverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.sample_receiver, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SampleReceiverBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout CL1 = (ConstraintLayout) rootView;

      id = R.id.guideline2;
      Guideline guideline2 = rootView.findViewById(id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = rootView.findViewById(id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.guideline4;
      Guideline guideline4 = rootView.findViewById(id);
      if (guideline4 == null) {
        break missingId;
      }

      id = R.id.receiverText;
      TextView receiverText = rootView.findViewById(id);
      if (receiverText == null) {
        break missingId;
      }

      id = R.id.receiverTime;
      TextView receiverTime = rootView.findViewById(id);
      if (receiverTime == null) {
        break missingId;
      }

      return new SampleReceiverBinding((ConstraintLayout) rootView, CL1, guideline2, guideline3,
          guideline4, receiverText, receiverTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}