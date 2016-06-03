package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bathla on 4/8/2016.
 */
public class MenuOption extends Activity{


    String[] titles=null;
    int images[] ={R.drawable.ic_attach,R.drawable.ic_email,R.drawable.ic_female,R.drawable.ic_male,R.drawable.ic_phone,R.drawable.ic_reset};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menulayout);

        titles = getResources().getStringArray(R.array.menuItems);
        GridView gridView = (GridView) findViewById(R.id.gridview);

        gridView.setAdapter(new ImageAdapter(titles,images));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Intent intent = new Intent(MenuOption.this,TopicsListActivity.class);
                startActivity(intent);
            }
        });
    }


    class ImageAdapter extends BaseAdapter
    {

        String[] titles;
        int[] images;
        ImageAdapter()
        {

        }
        ImageAdapter(String[] titles,int images[])
        {
            this.titles=titles;
            this.images= images;
        }

        @Override
        public int getCount() {
            return titles.length-1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            ImageView image=null;
         TextView title=null;

            LayoutInflater inflator = getLayoutInflater();
            final View grid =  inflator.inflate(R.layout.single_grid,parent,false);

            image = (ImageView) grid.findViewById(R.id.grid_image);
            title= (TextView) grid.findViewById(R.id.title_grid);

            image.setImageResource(this.images[position]);
            title.setText(this.titles[position]);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position==0)
                    {
                        Intent intent = new Intent(MenuOption.this,TopicsListActivity.class);
                        startActivity(intent);
                    }
                    else if(position==1)
                    {
                        Intent intent = new Intent(MenuOption.this,TopicsListActivity.class);
                        startActivity(intent);
                    }
                    else if(position==2)
                    {

                    }
                }
            });
            return grid;

        }


    }

}


