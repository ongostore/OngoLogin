package com.ongo.ongologinlibrary.login;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {

    @SerializedName("DOB")
    @Expose
    private String dOB;
    @SerializedName("panNumber")
    @Expose
    private String panNumber;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("secondaryEmail")
    @Expose
    private String secondaryEmail;
    @SerializedName("orgId")
    @Expose
    private Integer orgId;
    @SerializedName("SocialLoginId")
    @Expose
    private String socialLoginId;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("userImagePath")
    @Expose
    private String userImagePath;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("How did your hear about us")
    @Expose
    private String howDidYourHearAboutUs;
    @SerializedName("nomineeMobile")
    @Expose
    private String nomineeMobile;
    @SerializedName("AccountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("UpdatedUniqueUserName")
    @Expose
    private String updatedUniqueUserName;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("nomineeRelation")
    @Expose
    private String nomineeRelation;
    @SerializedName("userBannerPath")
    @Expose
    private String userBannerPath;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("userStatus")
    @Expose
    private String userStatus;
    @SerializedName("isAdmin")
    @Expose
    private String isAdmin;
    @SerializedName("aadharNumber")
    @Expose
    private String aadharNumber;
    @SerializedName("nomineeName")
    @Expose
    private String nomineeName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("isRefferalCodeUsed")
    @Expose
    private String isRefferalCodeUsed;
    @SerializedName("CouponValidTill")
    @Expose
    private String couponValidTill;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("PIN")
    @Expose
    private String pIN;
    @SerializedName("Langitude")
    @Expose
    private String langitude;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("RewardPoints")
    @Expose
    private String rewardPoints;
    @SerializedName("macId")
    @Expose
    private String macId;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("organisation")
    @Expose
    private String organisation;
    @SerializedName("referenceId")
    @Expose
    private String referenceId;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("UniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("IFSCCode")
    @Expose
    private String iFSCCode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lattitude")
    @Expose
    private String lattitude;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("nomineeEmail")
    @Expose
    private String nomineeEmail;
    @SerializedName("macIdJobId")
    @Expose
    private Integer macIdJobId;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    protected LoginResponse(Parcel in) {
        dOB = in.readString();
        panNumber = in.readString();
        bankName = in.readString();
        password = in.readString();
        city = in.readString();
        secondaryEmail = in.readString();
        if (in.readByte() == 0) {
            orgId = null;
        } else {
            orgId = in.readInt();
        }
        socialLoginId = in.readString();
        emailId = in.readString();
        gender = in.readString();
        longitude = in.readString();
        userImagePath = in.readString();
        lastName = in.readString();
        status = in.readString();
        howDidYourHearAboutUs = in.readString();
        nomineeMobile = in.readString();
        accountNumber = in.readString();
        updatedUniqueUserName = in.readString();
        msg = in.readString();
        country = in.readString();
        nomineeRelation = in.readString();
        userBannerPath = in.readString();
        email = in.readString();
        userStatus = in.readString();
        isAdmin = in.readString();
        aadharNumber = in.readString();
        nomineeName = in.readString();
        mobile = in.readString();
        age = in.readString();
        isRefferalCodeUsed = in.readString();
        couponValidTill = in.readString();
        referralCode = in.readString();
        state = in.readString();
        fullName = in.readString();
        image = in.readString();
        pIN = in.readString();
        langitude = in.readString();
        firstName = in.readString();
        rewardPoints = in.readString();
        macId = in.readString();
        description = in.readString();
        organisation = in.readString();
        referenceId = in.readString();
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        token = in.readString();
        uniqueId = in.readString();
        iFSCCode = in.readString();
        name = in.readString();
        address = in.readString();
        lattitude = in.readString();
        accountName = in.readString();
        nomineeEmail = in.readString();
        if (in.readByte() == 0) {
            macIdJobId = null;
        } else {
            macIdJobId = in.readInt();
        }
        mobileNo = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getSocialLoginId() {
        return socialLoginId;
    }

    public void setSocialLoginId(String socialLoginId) {
        this.socialLoginId = socialLoginId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHowDidYourHearAboutUs() {
        return howDidYourHearAboutUs;
    }

    public void setHowDidYourHearAboutUs(String howDidYourHearAboutUs) {
        this.howDidYourHearAboutUs = howDidYourHearAboutUs;
    }

    public String getNomineeMobile() {
        return nomineeMobile;
    }

    public void setNomineeMobile(String nomineeMobile) {
        this.nomineeMobile = nomineeMobile;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUpdatedUniqueUserName() {
        return updatedUniqueUserName;
    }

    public void setUpdatedUniqueUserName(String updatedUniqueUserName) {
        this.updatedUniqueUserName = updatedUniqueUserName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNomineeRelation() {
        return nomineeRelation;
    }

    public void setNomineeRelation(String nomineeRelation) {
        this.nomineeRelation = nomineeRelation;
    }

    public String getUserBannerPath() {
        return userBannerPath;
    }

    public void setUserBannerPath(String userBannerPath) {
        this.userBannerPath = userBannerPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIsRefferalCodeUsed() {
        return isRefferalCodeUsed;
    }

    public void setIsRefferalCodeUsed(String isRefferalCodeUsed) {
        this.isRefferalCodeUsed = isRefferalCodeUsed;
    }

    public String getCouponValidTill() {
        return couponValidTill;
    }

    public void setCouponValidTill(String couponValidTill) {
        this.couponValidTill = couponValidTill;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPIN() {
        return pIN;
    }

    public void setPIN(String pIN) {
        this.pIN = pIN;
    }

    public String getLangitude() {
        return langitude;
    }

    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIFSCCode() {
        return iFSCCode;
    }

    public void setIFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNomineeEmail() {
        return nomineeEmail;
    }

    public void setNomineeEmail(String nomineeEmail) {
        this.nomineeEmail = nomineeEmail;
    }

    public Integer getMacIdJobId() {
        return macIdJobId;
    }

    public void setMacIdJobId(Integer macIdJobId) {
        this.macIdJobId = macIdJobId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dOB);
        dest.writeString(panNumber);
        dest.writeString(bankName);
        dest.writeString(password);
        dest.writeString(city);
        dest.writeString(secondaryEmail);
        if (orgId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orgId);
        }
        dest.writeString(socialLoginId);
        dest.writeString(emailId);
        dest.writeString(gender);
        dest.writeString(longitude);
        dest.writeString(userImagePath);
        dest.writeString(lastName);
        dest.writeString(status);
        dest.writeString(howDidYourHearAboutUs);
        dest.writeString(nomineeMobile);
        dest.writeString(accountNumber);
        dest.writeString(updatedUniqueUserName);
        dest.writeString(msg);
        dest.writeString(country);
        dest.writeString(nomineeRelation);
        dest.writeString(userBannerPath);
        dest.writeString(email);
        dest.writeString(userStatus);
        dest.writeString(isAdmin);
        dest.writeString(aadharNumber);
        dest.writeString(nomineeName);
        dest.writeString(mobile);
        dest.writeString(age);
        dest.writeString(isRefferalCodeUsed);
        dest.writeString(couponValidTill);
        dest.writeString(referralCode);
        dest.writeString(state);
        dest.writeString(fullName);
        dest.writeString(image);
        dest.writeString(pIN);
        dest.writeString(langitude);
        dest.writeString(firstName);
        dest.writeString(rewardPoints);
        dest.writeString(macId);
        dest.writeString(description);
        dest.writeString(organisation);
        dest.writeString(referenceId);
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(token);
        dest.writeString(uniqueId);
        dest.writeString(iFSCCode);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(lattitude);
        dest.writeString(accountName);
        dest.writeString(nomineeEmail);
        if (macIdJobId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(macIdJobId);
        }
        dest.writeString(mobileNo);
    }
}
