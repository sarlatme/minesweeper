package com.example.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameFragment extends Fragment {
    private Game game;

    private int row, col, bombs;
    private String level;
    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_fragment, container, false);

        Bundle args = getArguments();
        if(args != null){
            row = args.getInt("row");
            col = args.getInt("col");
            bombs = args.getInt("bombs");
            level = args.getString("level");
        }

        game = new Game(getContext(),row, col, bombs, level);

        displayBoardView(view);

        return view;
    }


    private void displayBoardView(View view) {
        // Obtenir le conteneur pour la vue du tableau
        LinearLayout boardContainer = view.findViewById(R.id.board_container);

        // Obtenir les dimensions de l'écran et calculer la taille maximale de l'image
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // Calculer la hauteur disponible pour la carte en se basant sur la valeur layout_constraintHeight_percent.
        int boardHeight = (int) (screenHeight * 0.45);

        // Calculate the maximum image size based on the screen dimensions and board size
        int maxImageWidth = screenWidth / col;
        int maxImageHeight = boardHeight / row;
        int maxImageSize = Math.min(maxImageWidth, maxImageHeight);

        // Ajouter les ImageViews au conteneur
        for (int i = 0; i < game.getBoardView().getBoard().getRows(); i++) {
            LinearLayout rowLayout = new LinearLayout(getContext());
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    maxImageSize));
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < game.getBoardView().getBoard().getCols(); j++) {
                ImageView imageView = game.getBoardView().getParticularImageView(i, j);
                imageView.setImageResource(R.drawable.empty_case);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        maxImageSize,
                        maxImageSize));
                rowLayout.addView(imageView);
            }
            boardContainer.addView(rowLayout);
        }
    }
}