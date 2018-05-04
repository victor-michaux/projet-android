package efrei.edu.projetandroid.fragment;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import efrei.edu.projetandroid.R;
import efrei.edu.projetandroid.game.BallThrowType;
import efrei.edu.projetandroid.game.PlayerRound;
import efrei.edu.projetandroid.game.Round;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerThrowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerThrowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerThrowFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_CURRENT_ROUND = "currentRound";
    private static final String ARG_CURRENT_PLAYER_ROUND = "currentPlayerRound";

    private Round currentRound;
    private PlayerRound currentPlayerRound;

    private OnFragmentInteractionListener mListener;

    public PlayerThrowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentRound Parameter 1.
     * @param currentPlayerRound Parameter 2.
     * @return A new instance of fragment PlayerThrowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerThrowFragment newInstance(Round currentRound, PlayerRound currentPlayerRound) {
        PlayerThrowFragment fragment = new PlayerThrowFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CURRENT_ROUND, currentRound);
        args.putSerializable(ARG_CURRENT_PLAYER_ROUND, currentPlayerRound);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.currentRound = (Round)getArguments().getSerializable(ARG_CURRENT_ROUND);
            this.currentPlayerRound = (PlayerRound)getArguments().getSerializable(ARG_CURRENT_PLAYER_ROUND);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_throw, container, false);

        TextView playerName = view.findViewById(R.id.player_name);
        System.err.println(this.currentRound.isFinished());
        System.err.println(this.currentRound.getCurrentPlayer());
        playerName.setText(this.currentRound.getCurrentPlayer().getNom());

        TextView throwName = view.findViewById(R.id.throw_count);
        throwName.setText(this.currentPlayerRound.getFirstThrow() == null ? "Lancer 1" : "Lancer 2");

        Button gutterButton = (Button) view.findViewById(R.id.gutter_button);
        gutterButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gutter_button:
                this.currentPlayerRound.play(BallThrowType.GUTTER, null);
                break;
        }

        Uri noteUri = Uri.parse("OKOKOOKOKOKO");
        mListener.onFragmentInteraction(noteUri);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
