package com.pkr.eventargs;

import java.util.ArrayList;

/**
 * Created by PK on 4/22/2018.
 */

public class Model {

    String itemString;
    Boolean isChecked = false;

    public Model(String itemString) {
        this.itemString = itemString;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
