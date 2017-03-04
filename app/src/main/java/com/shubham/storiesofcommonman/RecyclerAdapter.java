package com.shubham.storiesofcommonman;

/**
 * Created by SHUBHAM on 3/3/2017.
 */

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{

    private String[] titles = {"Joy Solomon",
            "Sakthi Swaroop Gowda",
            "Vijay Krishnam ",
            "Nagarjuna Yelisetty",
            "Amirthavarshini. R ",
            "Krishna Gopika",
            "Kruthika Parappa"
            };

    private String[] details = {"Chief-Editor",
            "CEO", "Designer",
            "Chief Technical Advisor", "Writer",
            "Writer", "Writer"
            };

    private String[] descp = {"Music producer by profession","NITian, wikipedian, Software engineer","Loves Marketing and designing","NIT-Alumni.Lives in Bangalore, Software Developer, Loves and mentor’s Startups.","Studying B.Sc. Plant Biology and Plant Biotechnology, Women’s Christian College, Chennai .Loves Food (eating and cooking); Running (I’m an athlete); Teddy Bears (most soft toys, actually),Books, TV, Games(Subway Surfers and the likes,Candy Crush and the likes) Likes: Dance; Music (Listening mostly, singing when I’m alone); Art (Doodling, Painting)","Graduate in B.A English , holds a  Post Graduate Degree in Journalism ,  loves to read books and also pens down , she can also play veena , Great fan of the “COMPLETE ACTOR” Mohan lal ,loves to dance and also listens to melodious songs"," I’m a-gonna-be-engineer by profession and an aspiring writer. I’m a person who loves books more than anything else. Listening to music and surfing the net are my other hobbies."};


    private int[] images = { R.drawable.android_image_1,
            R.drawable.android_image_2,
            R.drawable.android_image_3,
            R.drawable.android_image_4,
            R.drawable.android_image_5,
            R.drawable.android_image_6,
            R.drawable.android_image_7,
             };

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView itemdescrp;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_imagec);
            itemTitle = (TextView)itemView.findViewById(R.id.item_titlec);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detailc);
            itemdescrp = (TextView)itemView.findViewById(R.id.item_descpc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
        viewHolder.itemdescrp.setText(descp[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}