package com.example.weatherapp321.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 *  "cityid":"101010100",
 *     "city":"北京",
 *     "cityEn":"beijing",
 *     "country":"中国",
 *     "countryEn":"China",
 *     "update_time":"2021-07-25 16:03:06",
 *
 *     gson
 */
public class WeatherBean implements Serializable {

    @SerializedName("cityid")
    private String cityId;

    @SerializedName("city")
    private String city;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeathers;


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<DayWeatherBean> getDayWeathers() {
        return dayWeathers;
    }

    public void setDayWeathers(List<DayWeatherBean> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", dayWeathers=" + dayWeathers +
                '}';
    }
}
