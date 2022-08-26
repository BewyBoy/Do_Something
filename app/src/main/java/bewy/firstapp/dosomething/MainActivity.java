package bewy.firstapp.dosomething;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

   /* private int a;*/

    boolean first_time_opened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {
        });



        ProgressBar progressBar = findViewById(R.id.progress_bar);
        EasyFlipView easyFlipView = findViewById(R.id.flip);
        easyFlipView.flipTheView();

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView title = findViewById(R.id.title_main);
        TextView description = findViewById(R.id.des_main);
        ImageView image = findViewById(R.id.imageView_main);

        /* TextView textView = findViewById(R.id.textView);*/

        SharedPreferences sp = getSharedPreferences("GenPref", MODE_PRIVATE);

        /*a = sp.getInt("a", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = sp.edit();



        Button btn = findViewById(R.id.button);
        Random random = new Random();
        first_time_opened = true;


        btn.setOnClickListener(view -> {
            if (easyFlipView.isBackSide()) {
                int val = random.nextInt(17);
                if (first_time_opened){
                    progressBar.setVisibility(View.VISIBLE);
                    Thread thread = new Thread(() -> {
                        for (int i = 0; i < 100; i += 1) {
                            progressBar.incrementProgressBy(1);
                            SystemClock.sleep(8);
                        }
                        SystemClock.sleep(50);
                        progressBar.setProgress(0);
                    });
                    thread.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            easyFlipView.setVisibility(View.VISIBLE);
                            easyFlipView.flipTheView();
                        }
                    }, 825);
                    /*header.setVisibility(View.INVISIBLE);*/
                    first_time_opened = false;
                    execute(image, title, description, val);
                    btn.setText("OK");
                } else {
                    execute(image, title, description, val);
                    easyFlipView.flipTheView();
                    btn.setText("OK");
                }
            } else {
                easyFlipView.flipTheView();
                btn.setText("GENERATE");
            }
            btn.setEnabled(false);

            //enable button after 1000 millisecond
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                btn.setEnabled(true);
            }, 700);

        });

        ImageButton imageButton = findViewById(R.id.setting);
        imageButton.setOnClickListener(view -> {
            setting(sp, editor);

        });

    }



    private void execute(ImageView image, TextView title, TextView description, int id){
        Idea idea = interpret(id);
        System.out.println(idea.toString());
        image.setImageResource(idea.getPic());
        title.setText(idea.getTitle());
        description.setText(idea.getDescription());

    }

    private Idea interpret(int id){
        Random random2 = new Random();
        Idea result;
        switch (id) {
            case 1:
                result = new Idea("Draw something",  R.mipmap.draw);
                String[] draw_this = {"a cat that is a wizard", "sir dog Lancelot", "a cool unicorn", "a magical sword",
                        "a funny interpretation of amongus", "the sky full of clouds", "a flower", "a bottle of water",
                        "an interesting set of clothes"};
                String object = draw_this[random2.nextInt(draw_this.length)];
                result.setDescription("Take off your mind by drawing something. Don't pressure yourself to make it look perfect, everything has beauty in its own way." +
                        "In case you don't know what to draw, let's draw " + object);
                break;
            case 2:
                result = new Idea("Watch something", R.mipmap.film);
                String[] watch_this = {"Harry Potter", "Avenger","When Harry met Sally", "John Wick",
                        "Spider-man: Into the SpiderVerse", "The silence of the Lambs", "Inside out", "Howl and the moving castle",
                        "Now you see me"};
                String film = "<b>" + watch_this[random2.nextInt(watch_this.length)] + "</b> ";
                result.setDescription("My film suggestion for today is: \n "+ film);
                break;
            case 3:
                result = new Idea("Play a game", R.mipmap.play);

                String[] lets_game = {"amogus"};
                String game = lets_game[random2.nextInt(lets_game.length)];
                result.setDescription("Let's play "+ game + " and have some fun!");
                break;
            case 4:
                result = new Idea("Exercise", R.mipmap.exercise);

                String[] exercises = {"go for a run", "do 20 jump jack", "do 10 crunch", "stretch your body"};
                String exercise = exercises[random2.nextInt(exercises.length)];
                result.setDescription("Time to get your blood flowing, by doing some exercises. Let's " +
                        exercise);
                break;
            case 5:
                result = new Idea("Cooking" ,R.mipmap.cook);

                String[] cooking = {"French Omelette", "Fried Rice", "Fried Noodle", "French Fries", "Grilled Cheese",
                        "Roast Chicken (Tip: use oyster sauce!)", "Marshmallow (actually more simple than you thought)",
                        "Honeycomb candy", "Vegetable soup with herbs", "Cream based soup"};
                String food = cooking[random2.nextInt(cooking.length)];
                result.setDescription("Let's practice your" +
                        " skill and make it perfect by making some "+ food + ".\nIf you are new, a " +
                        "good tip is cook in small amount so that if you make a mistake, you don't " +
                        "waste too much food!");
                break;
            case 6:
                result = new Idea("Text/Call a friend", R.mipmap.call);

                result.setDescription("Talk to your mom. She would love to hear from you.");
                break;
            case 7:
                result = new Idea("Cleaning!",R.mipmap.clean);

                String[] house_work = {"cleaning your bookshelves. Give them a good wipe.",
                        "rearranging your kitchen. Put your spices into jars and wipe up the spilled waste in your cabinets. Hygiene is BIG for your health.",
                        "sweeping and mopping the floor. No more dust on your feet!",
                };
                String work = house_work[random2.nextInt(house_work.length)];
                result.setDescription("Clean up your place. Being clean keep you healthy and productive! \n" +
                        "Let's start with "+ work);
                break;
            case 8:
                result = new Idea("Go somewhere", R.mipmap.go_out);

                String[] places = {"the park and breath some fresh air!",
                        "the museum and learn about art or history",
                        "the cinema and watch a movie at a big screen. Get your friends and have a good time",
                        "the zoo, see the animals and feed them if you can. Support the wildlife!",
                        "a restaurant and treat yourself for the next meal of the day.",
                        "a cafe and spend time with your friends. Gossiping >:3",
                        "a lake and walk. Having some time near a body of water calm you they say.",
                        "a supermarket. Buy some grocery and something you like. It's also fine to just look" +
                                "around",
                        "a shopping mall. Window-shopping is fun! And there might be an arcade in there",
                        "the park! Have some alone time and see everyone around you."};
                String place = places[random2.nextInt(places.length)];
                result.setDescription("It's a good idea to know your own city. Go to " + place);
                break;
            case 9:
                result = new Idea("Read a book", R.mipmap.read);

                String[] books = {"The Black Tulip (Alexandre Dumas)", "The Rosie Project (Graeme Simsion)",
                        "Da Vinci Code (Dan Brown)", "If Only It Were True (Marc Levy)", "Proof (Dick Francis)"};
                String book = books[random2.nextInt(books.length)];
                result.setDescription("If you haven't read a book in a while, it might be hard to concentrate." +
                        " If that's the case, try to read 10 pages and you can rest.\n" +
                        "My book suggestion for today is: " + book);
                break;
            /*case 10:
                ideasList.add(new Idea("Listen to music", 10));

            idea.setDescription("I like planet her of Doja");
                break;
                */
            case 11:
                result = (new Idea("Rest","Just sit down and breath", R.mipmap.rest));
                break;
            case 12:
                result = (new Idea("Continue!", "Do you have any project of your own? Let's get back to it", R.mipmap.continuee));
                break;
            case 13:
                result = (new Idea("Plan your day", "See if you want to do something today", R.mipmap.planning));
                break;

            /*

            case 14:
                String[] website = {"amogus"};
                ideasList.add(new Idea("Check out this website",14));

                idea.setDescription("Do you know about this website?");
                break;
            case 15:
                ideasList.add(new Idea("Challenge yourself", 15));

                idea.setDescription("Do a really hard thing");
                break;*/
            /*case 16:
                ideasList.add(new Idea("Learn something new!", 16));

                idea.setDescription("Learn juggle");
                break;*/
            case 17:
                result = (new Idea("Be a creator!",R.mipmap.create));

                result.setDescription("Write a script, make a beat, get your friend to do a sketch you made," +
                        " time to start being creative and have fun with your imagination");
                break;
            default:
                result = new Idea();
        }
        return result;
    }

    private void setting(SharedPreferences sp, SharedPreferences.Editor editor){

        Dialog menu = new Dialog(this);
        menu.setContentView(R.layout.menu);

        RadioGroup radio = menu.findViewById(R.id.radio_group);
        Button cancel = menu.findViewById(R.id.cancel);
        Button apply = menu.findViewById(R.id.apply);
        AtomicInteger mode = new AtomicInteger();
        menu.show();
        radio.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i){
                case R.id.normal_mode:
                    break;
                case R.id.focus_mode:
                    Toast.makeText(this,R.string.warning, Toast.LENGTH_SHORT).show();
                    mode.set(1);
                    break;
            }

        });
        apply.setOnClickListener(view -> {
            if (mode.get() == 1){
                toFocus(sp, editor);
            }
            menu.dismiss();
        });
        cancel.setOnClickListener(view -> menu.dismiss());

    }

    private void toFocus(SharedPreferences sp, SharedPreferences.Editor editor){
        System.out.println(sp.getBoolean("First_time_today", true));
        if( sp.getBoolean("First_time_today", true)) {
            editor.putBoolean("First_time_today", false);
            editor.putInt("Times_left", 3);
            editor.apply();
        }
        Intent intent = new Intent(this, Focus_mode.class);
        startActivity(intent);
        finish();
    }

}