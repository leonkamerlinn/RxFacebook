package com.leon.library;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacebookUser {
    private String id;
    private String name;
    private String link;
    @SerializedName("age_range")
    private AgeRange ageRange;
    private String birthday;
    private Context context;
    private String email;
    @SerializedName("first_name")
    private String firstName;
    private String gender;
    @SerializedName("last_name")
    private String lastName;
    private Location location;
    private String locale;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }


    public static class Location {

        private String id;
        private String name;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


    public static class MutualFriend {

        private List data;
        private Summary summary;


        public List getData() {
            return data;
        }

        public void setData(List data) {
            this.data = data;
        }

        public Summary getSummary() {
            return summary;
        }

        public void setSummary(Summary summary) {
            this.summary = summary;
        }

    }


    public static class MutualLike {

        private List<Object> data;
        private Summary summary;


        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }

        public Summary getSummary() {
            return summary;
        }

        public void setSummary(Summary summary) {
            this.summary = summary;
        }

    }


    public static class Summary {

        @SerializedName("total_count")
        private Long totalCount;


        public Long getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Long totalCount) {
            this.totalCount = totalCount;
        }

    }

    public static class AgeRange {

        private Long min;


        public Long getMin() {
            return min;
        }

        public void setMin(Long min) {
            this.min = min;
        }

    }

    public static class Context {

        @SerializedName("mutual_friends")
        private MutualFriend mutualFriends;
        @SerializedName("mutual_likes")
        private MutualLike mutualLikes;
        private String id;


        public MutualFriend getMutualFriend() {
            return mutualFriends;
        }

        public void setMutualFriend(MutualFriend mutualFriends) {
            this.mutualFriends = mutualFriends;
        }

        public MutualLike getMutualLike() {
            return mutualLikes;
        }

        public void setMutualLike(MutualLike mutualLikes) {
            this.mutualLikes = mutualLikes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
}
