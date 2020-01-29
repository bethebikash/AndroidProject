package com.bhattaraibikash.erepair.fragment.main;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.adapter.CategoryAdapter;
import com.bhattaraibikash.erepair.adapter.ServiceAdapter;
import com.bhattaraibikash.erepair.adapter.SliderAdapter;
import com.bhattaraibikash.erepair.api.CategoryApi;
import com.bhattaraibikash.erepair.models.Category;
import com.bhattaraibikash.erepair.models.Service;
import com.bhattaraibikash.erepair.url.Url;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SliderView sliderView;
    private RecyclerView rvCategory;
    private RecyclerView rvAllService;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Slider
        sliderView = view.findViewById(R.id.imageSlider);

        final SliderAdapter adapter = new SliderAdapter(getContext());
        adapter.setCount(5);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });

        // Category Recycler view
        rvCategory = view.findViewById(R.id.rvCategory);

        CategoryApi categoryApi = Url.getInstance().create(CategoryApi.class);

        Call<List<Category>> listCall = categoryApi.getCategories();
        listCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoryList = response.body();

                CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categoryList);
                rvCategory.setAdapter(categoryAdapter);
                rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed:" + t, Toast.LENGTH_SHORT).show();
            }
        });

        // Service Recycler view
        rvAllService = view.findViewById(R.id.rvAllService);

        List<Service> serviceList =new ArrayList<>();
        serviceList.add(new Service("0222520", "Title one", "the description", "2514", "image", "category"));
        serviceList.add(new Service("5454851", "Title two", "icon", "2514", "image", "category"));
        serviceList.add(new Service("2125648", "Title three", "icon", "2514", "image", "category"));
        serviceList.add(new Service("2125648", "Title four", "icon", "2514", "image", "category"));
        serviceList.add(new Service("2125648", "Title five", "icon", "2514", "image", "category"));

        ServiceAdapter serviceAdapter = new ServiceAdapter(getContext(), serviceList);
        rvAllService.setAdapter(serviceAdapter);
        rvAllService.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return (view);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case R.id.menuSearch:
                msg = "Search Checked";
                break;
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
