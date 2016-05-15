package commands;

import java.util.Optional;

/**
 * Created by Allin on 5/11/2016.
 */
public class Filter {
    private Boolean enabled;

    Filter(Boolean enabled, Optional<String> dict){
        this.enabled = enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
