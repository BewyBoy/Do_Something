package bewy.firstapp.dosomething;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Focus_mode extends AppCompatActivity {
    int times_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.focus_mode);
        Button button = findViewById(R.id.button_focus);
        TextView textView = findViewById(R.id.times_left);
        ListView listView = findViewById(R.id.list_task);
        ArrayList<Idea> tasks = new ArrayList<>();
        ArrayList<String> task_string = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, task_string);
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences = getSharedPreferences("GenPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        times_left = sharedPreferences.getInt("Times_left",0);
        System.out.println( times_left);
        textView.setText("Generate times left: "+ times_left);

        button.setOnClickListener(view -> {

            if (times_left > -100){
                popup(tasks, task_string);
                times_left--;
                textView.setText("Generate times left: "+ times_left);
                editor.putInt("Times_left", times_left);
                editor.apply();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "You are out of retry today", Toast.LENGTH_SHORT).show();
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popup2(tasks.get(i));
            }
        });
    }

    private void popup(ArrayList<Idea> tasks , ArrayList<String> task_str/*ArrayList<Integer> frozen , TextView textView*/) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_dialog);
        dialog.setCancelable(false);



        Random random = new Random();
        int val = random.nextInt(17);
        /*int chosen = ideasList.get(val);*/
        /*ideasList.remove(val);
        frozen.add(chosen);

        if (frozen.size() > 5 ) {
            ideasList.add(frozen.get(0));
            frozen.remove(0);
        }*/

        Idea get = interpret(val);
        tasks.add(get);
        task_str.add(get.getTitle());
        TextView title = dialog.findViewById(R.id.title_popup);
        title.setText(get.getTitle());
        TextView description = dialog.findViewById(R.id.des_popup);
        description.setText(Html.fromHtml(get.getDescription()));
        ImageView imageView = dialog.findViewById(R.id.image);
        imageView.setImageResource(get.getPic());

        dialog.show();
        Button btnClose = dialog.findViewById(R.id.close);
        btnClose.setOnClickListener(view -> {
            dialog.dismiss();
            /*textView.setText("Last Generated: \t" + chosen.getTitle());*/
        });
    }
    private void popup2(Idea get){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up_dialog);
        dialog.setCancelable(false);
        TextView title = dialog.findViewById(R.id.title_popup);
        title.setText(get.getTitle());
        TextView description = dialog.findViewById(R.id.des_popup);
        description.setText(Html.fromHtml(get.getDescription()));
        ImageView imageView = dialog.findViewById(R.id.image);
        imageView.setImageResource(get.getPic());
        dialog.show();
        Button btnClose = dialog.findViewById(R.id.close);
        btnClose.setOnClickListener(view -> {
            dialog.dismiss();
            /*textView.setText("Last Generated: \t" + chosen.getTitle());*/
        });
    }

    private Idea interpret(int id){
        Random random2 = new Random();
        new Idea("", 0);
        Idea result;
        switch (id) {
            case 1:
                result = new Idea("Draw something",  R.mipmap.draw,1);
                String[] draw_this = {"a cat that is a wizard", "sir dog Lancelot", "a cool unicorn", "a magical sword",
                        "a funny interpretation of amongus", "the sky full of clouds", "a flower", "a bottle of water",
                        "an interesting set of clothes"};
                String object = draw_this[random2.nextInt(draw_this.length)];
                result.setDescription("Take off your mind by drawing something. Don't pressure yourself to make it look perfect, everything has beauty in its own way." +
                        "In case you don't know what to draw, let's draw " + object);
                break;
            case 2:
                result = new Idea("Watch something", R.mipmap.film,2);
                String[] watch_this = {"Harry Potter", "Avenger","When Harry met Sally", "John Wick",
                        "Spider-man: Into the SpiderVerse", "The silence of the Lambs", "Inside out", "Howl and the moving castle",
                        "Now you see me"};
                String film = "<b>" + watch_this[random2.nextInt(watch_this.length)] + "</b> ";
                result.setDescription("My film suggestion for today is: \n "+ film);
                break;
            case 3:
                result = new Idea("Play a game", R.mipmap.play,3);

                String[] lets_game = {"amogus"};
                String game = lets_game[random2.nextInt(lets_game.length)];
                result.setDescription("Let's play "+ game + " and have some fun!");
                break;
            case 4:
                result = (new Idea("Exercise", R.mipmap.exercise,4));

                String[] exercises = {"go for a run", "do 20 jump jack", "do 10 crunch", "stretch your body"};
                String exercise = exercises[random2.nextInt(exercises.length)];
                result.setDescription("Time to get your blood flowing, by doing some exercises. Let's " +
                        exercise);
                break;
            case 5:
                result = (new Idea("Cooking" ,R.mipmap.cook, 5));

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
                result = (new Idea("Text/Call a friend", R.mipmap.call, 6));

                result.setDescription("Talk to your mom. She would love to hear from you.");
                break;
            case 7:
                result = (new Idea("Cleaning!",R.mipmap.clean,7));

                String[] house_work = {"cleaning your bookshelves. Give them a good wipe.",
                        "rearranging your kitchen. Put your spices into jars and wipe up the spilled waste in your cabinets. Hygiene is BIG for your health.",
                        "sweeping and mopping the floor. No more dust on your feet!",
                };
                String work = house_work[random2.nextInt(house_work.length)];
                result.setDescription("Clean up your place. Being clean keep you healthy and productive! \n" +
                        "Let's start with "+ work);
                break;
            case 8:
                result = (new Idea("Go somewhere", R.mipmap.go_out,8));

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
                result = (new Idea("Read a book", R.mipmap.read,9));

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
                result = (new Idea("Rest","Just sit down and breath", R.mipmap.rest,11));
                break;
            case 12:
                result = (new Idea("Continue!", "Do you have any project of your own? Let's get back to it", R.mipmap.continuee, 12));
                break;
            case 13:
                result = (new Idea("Plan your day", "See if you want to do something today", R.mipmap.planning, 13));
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
                result = (new Idea("Be a creator!",R.mipmap.create ,17));

                result.setDescription("Write a script, make a beat, get your friend to do a sketch you made," +
                        " time to start being creative and have fun with your imagination");
                break;
            default:
                result = new Idea("zzz", 20);
        }
        return result;
    }
}
