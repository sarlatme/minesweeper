package com.example.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameFragment extends Fragment {
    private Game game;

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_fragment, container, false);

        game = new Game(getContext(),3, 3, 10);

        displayBoardView(view);

        return view;
    }


    private void displayBoardView(View view) {
        // Get the container for the board view
        LinearLayout boardContainer = view.findViewById(R.id.board_container);

        // Add the ImageViews to the container
        int imageWidth = 30;
        int imageHeight = 30;
        for (int i = 0; i < game.getBoardView().getBoard().getRows(); i++) {
            LinearLayout rowLayout = new LinearLayout(getContext());
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    imageHeight));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < game.getBoardView().getBoard().getCols(); j++) {
                ImageView imageView = game.getBoardView().getParticularImageView(i, j);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        imageWidth,
                        imageHeight));
                rowLayout.addView(imageView);
            }
            boardContainer.addView(rowLayout);
        }
    }
}