package bewy.firstapp.dosomething;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Html;
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

    boolean first_time_opened, isFirst_time_opened_menu;

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
        ImageView header = findViewById(R.id.head);
        header.setImageResource(R.mipmap.head);

        SharedPreferences sp = getSharedPreferences("GenPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        isFirst_time_opened_menu = true;

        Button btn = findViewById(R.id.button);
        Random random = new Random();
        first_time_opened = true;


        btn.setOnClickListener(view -> {
            if (easyFlipView.isBackSide()) {

                String[] titles = {"Draw something", "Watch something", "Play a game", "Exercise", "Cooking", "Text/Call a friend",
                        "Cleaning!", "Go somewhere","Read a book","Rest", "Continue!","Plan your day"};
                int val = random.nextInt(titles.length);
                String get = titles[val];
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
                    header.setVisibility(View.INVISIBLE);
                    first_time_opened = false;
                    execute(image, title, description, get);
                    btn.setText("OK");
                } else {
                    execute(image, title, description, get);
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



    private void execute(ImageView image, TextView title, TextView description, String id){
        System.out.println(id);
        Idea idea = interpret(id);
        System.out.println(idea.toString());
        image.setImageResource(idea.getPic());
        title.setText(idea.getTitle());
        description.setText(Html.fromHtml(idea.getDescription()));

    }

    private Idea interpret(String category){
        Random random2 = new Random();

        Idea result = new Idea(category);
        String[] list;
        String object;

        switch (category) {
            case "Draw something":
                result.setPic(R.mipmap.draw);
                list = new String[]{"a cat that is a wizard", "sir dog Lancelot", "a cool unicorn", "a magical sword"
                        , "the sky full of clouds", "a flower", "a bottle of water",
                        "an interesting set of clothes"};
                object = list[random2.nextInt(list.length)];
                result.setDescription("Take off your mind by drawing something." +
                        "In case you don't know what to draw, let's draw " + object);
                break;
            case "Watch a movie":
                result.setPic(R.mipmap.film);
                list = new String[]{"Harry Potter", "Avenger", "When Harry met Sally", "John Wick",
                        "Spider-man: Into the SpiderVerse", "The silence of the Lambs", "Inside out", "Howl and the moving castle",
                        "Now you see me"};
                object = "<b>" + list[random2.nextInt(list.length)] + "</b> ";
                result.setDescription("My movie suggestion for today is: \n "+ object);
                break;
            case "Play a game":
                result.setPic(R.mipmap.play);

                list = new String[]{
                        "Roaring Streets\n" + "https://cottontrek.itch.io/roaring-streets",
                        "Vintage Flashlight\n" +"https://dylsdestiny.itch.io/vintage-flashlight",
                "AAAAA!!!\n" +
                        "https://bynine.itch.io/aaaaa ",
                "A Slime’s Quest for Freedom\n"+ "https://hrgames.itch.io/a-slimes-quest-for-freedom",
                "Geflect\n" +
                        "https://gaziter.itch.io/geflectgame",
                "Magnetic\n" +
                        "https://prodigalson.itch.io/magnetic\n",
                "Orbital Punch\n"+"https://ubertuber.itch.io/orbital-punch",
                "Barrage \n" +
                        "https://kano-stuff.itch.io/-barrage-",
                "Delta\n" +
                        "https://culturebosh.itch.io/delta",
                "Plover Parent\nhttps://albatrosssoup.itch.io/plover-parent",
                "Oeufwakening\n https://quentindelvallet.itch.io/oeufwakening",
                "Stuck Recoil \nhttps://matheuscunegato.itch.io/stuck-recoil",
                "ODA\n" +
                        "https://sicerat.itch.io/oda",
                "Void\n" +
                        "https://nicmagnier.itch.io/void",
                "HOPSHOT\n" +
                        "https://bearishmushroom.itch.io/hopshot",
                "powerCore\n" +
                        "https://sanjeev.itch.io/powercore",
                "Lockstep\n" +
                        "https://aioob.itch.io/lockstep",
                "Resize\n" +
                        "https://zorro-svardendahl.itch.io/resize",
                "SuperFunkyLightShow\n https://owensenior.itch.io/superfunkylightshow"};
                object = list[random2.nextInt(list.length)];
                result.setDescription("Let's play "+ object + " and have some fun!");
                break;
            case "Exercise":
                result.setPic(R.mipmap.exercise);

                list = new String[]{"go for a run", "do 20 jump jack", "do 10 crunch", "stretch your body"};
                object = list[random2.nextInt(list.length)];;
                result.setDescription("Time to get your blood flowing, by doing some exercises. Let's " +
                        object);
                break;
            case "Cooking":
                result.setPic(R.mipmap.cook);

                list = new String[]{"French Omelette", "Fried Rice", "Fried Noodle", "French Fries", "Grilled Cheese",
                        "Roast Chicken (Tip: use oyster sauce!)", "Marshmallow (actually more simple than you thought)",
                        "Honeycomb candy", "Vegetable soup with herbs", "Cream based soup"};
                object = list[random2.nextInt(list.length)];
                result.setDescription("Let's practice your" +
                        " skill and make it perfect by making some "+ object + ".");
                break;
            case "Text/Call a friend":
                result.setPic(R.mipmap.call);
                result.setDescription("Talk to your mom. She would love to hear from you.");
                break;
            case "Cleaning!":
                result.setPic(R.mipmap.clean);

                list = new String[]{"cleaning your bookshelves. Give them a good wipe.",
                        "rearranging your kitchen. Put your spices into jars and wipe up the spilled waste in your cabinets.",
                        "sweeping and mopping the floor. No more dust on your feet!",
                };
                object = list[random2.nextInt(list.length)];
                result.setDescription("Clean up your place. Being clean keep you healthy and productive! \n" +
                        "Let's start with "+ object);
                break;
            case "Go somewhere":
                result.setPic(R.mipmap.go_out);

                list = new String[]{"the park and breath some fresh air!",
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
                object = list[random2.nextInt(list.length)];
                result.setDescription("It's a good idea to know your own city. Go to " + object);
                break;
            case "Read a book":
                result.setPic(R.mipmap.read);

                list = new String[]{"The Black Tulip (Alexandre Dumas)", "The Rosie Project (Graeme Simsion)",
                        "Da Vinci Code (Dan Brown)", "If Only It Were True (Marc Levy)", "Proof (Dick Francis)"};
                object = list[random2.nextInt(list.length)];;
                result.setDescription("If you haven't read a book in a while, it might be hard to concentrate." +
                        " If that's the case, try to read 10 pages and you can rest.\n" +
                        "My book suggestion for today is: " + object);
                break;
            /*case 10:
                ideasList.add(new Idea("Listen to music", 10));

            idea.setDescription("I like planet her of Doja");
                break;
                */
            case "Rest":
                result.setPic(R.mipmap.rest);
                result.setDescription("Time to take a nap");
                break;
            case "Continue!":
                result.setDescriptionAndPic("Do you have any project of your own? Let's get back to it", R.mipmap.continuee);
                break;
            case "Plan your day":
                result.setDescriptionAndPic( "See if you want to do something today", R.mipmap.planning);
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
            /*case 17:
                result = (new Idea("Be a creator!",R.mipmap.create ,17));

                result.setDescription("Write a script, make a beat, get your friend to do a sketch you made," +
                        " time to start being creative and have fun with your imagination");
                break;*/
            default:
                result = new Idea();
                break;
        }
        return result;
    }

    private void setting(SharedPreferences sp, SharedPreferences.Editor editor){

        Dialog menu = new Dialog(this);
        menu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        menu.setContentView(R.layout.menu);
        menu.setCancelable(false);
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
                    if (isFirst_time_opened_menu) {
                        Toast.makeText(this, R.string.warning, Toast.LENGTH_LONG).show();
                        isFirst_time_opened_menu = false;
                    }
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