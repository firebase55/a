/*
 * Copyright 2015 Blanyal D'Souza.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih.Model;

// Reminder class
public class Reminder {
    private int mID;
    private String mTitle;

    private String mDate;
    private String mTime;
    private String mActive;


    public Reminder(int ID, String Title, String Date, String Time, String Active) {
        mID = ID;
        mTitle = Title;
        mDate = Date;
        mTime = Time;
        mActive = Active;
    }

    public Reminder(String Title, String Date, String Time, String Active) {
        mTitle = Title;
        mDate = Date;
        mTime = Time;
        mActive = Active;
    }

    public Reminder() {
    }


    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "mID=" + mID +
                ", mTitle='" + mTitle + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mActive='" + mActive + '\'' +
                '}';
    }
}
