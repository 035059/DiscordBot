package main.java.commands;

import java.util.Optional;

/**
 * Created by Allin on 5/11/2016.
 */
public final class Filter {
    private Boolean enabled;

    private Filter(){}

    public void run(Boolean enabled, Optional<String> dict){
        enabled = enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
