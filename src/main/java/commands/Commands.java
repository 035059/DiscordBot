package commands;

/**
 * Created by Allin on 5/12/2016.
 */
public enum Commands {

    Delete() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Filter() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Google() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Horn() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Lookup() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Meme() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Message() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    Music() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    WolframAlpha() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    ONE() {
        @Override public void doSomething() {
            System.out.println("User selected command #1");
        }
    },
    TWO() {
        @Override public void doSomething() {
            System.out.println("User selected command #2");
        }
    };

    public abstract void doSomething();
}
