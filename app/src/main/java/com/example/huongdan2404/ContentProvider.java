package com.example.huongdan2404;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContentProvider {
    private Activity activity;
    public ContentProvider(Activity activity) { this.activity = activity;}
    public String phoneNumberOfStudent(Student student) {
        String name = student.getName();
        String phoneNumber = null;

        // Define the columns to query
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

        // Define the selection criteria
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?";
        String[] selectionArgs = {name};

        // Query the Contacts content provider
        ContentResolver contentResolver = activity.getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
        );

        // Retrieve the phone number if the cursor is not null
        if (cursor != null && cursor.moveToFirst()) {
            int phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            phoneNumber = cursor.getString(phoneNumberIndex);
            cursor.close();
        }
        return phoneNumber;
    }
}
