package org.crypto.hydro.auth;

import com.google.gson.annotations.SerializedName;

public class Whitelist extends BaseApiResponse {

    @SerializedName("hydro_address_id")
    private String hydroAddressId;

    public String getHydroAddressId() {
        return hydroAddressId;
    }

    public void setHydroAddressId(String hydroAddressId) {
        this.hydroAddressId = hydroAddressId;
    }

    @Override
    public String toString() {
        return "Whitelist{" +
                "success='" + super.isSuccess() + '\'' +
                "message='" + super.getMessage() + '\'' +
                "hydroAddressId='" + hydroAddressId + '\'' +
                '}';
    }
}
