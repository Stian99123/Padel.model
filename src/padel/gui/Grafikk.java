package padel.hoved;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import padel.modell.Kamp;
import padel.modell.Lag;

public class Grafikk {

    private Kamp padelKamp;
    private JFrame frame;
    private JLabel scoreLabel;
    private JButton team1PointButton;
    private JButton team2PointButton;
    private JButton exitButton;

    public Grafikk(Kamp kamp) {
        this.padelKamp = kamp;

        frame = new JFrame("Padel Score Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame.setLayout(new BorderLayout());

        initializeComponents();
        layoutComponents();

        updateScoreDisplay(padelKamp.hentHeleStillingen());

        frame.setVisible(true);
    }

    private void initializeComponents() {
        scoreLabel = new JLabel("Laster...", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));

        team1PointButton = new JButton("Poeng: " + padelKamp.getLag1().getNavn());
        team2PointButton = new JButton("Poeng: " + padelKamp.getLag2().getNavn());

        team1PointButton.addActionListener(new PoengListener(padelKamp.getLag1()));
        team2PointButton.addActionListener(new PoengListener(padelKamp.getLag2()));

        exitButton = new JButton("Avslutt Program");
        exitButton.addActionListener(e -> System.exit(0));
    }
    private void layoutComponents() {
        JPanel teamButtonPanel = new JPanel();
        teamButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        teamButtonPanel.add(team1PointButton);
        teamButtonPanel.add(team2PointButton);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        exitPanel.add(exitButton);

        JPanel centerContentPanel = new JPanel();
        centerContentPanel.setLayout(new BoxLayout(centerContentPanel, BoxLayout.Y_AXIS));

        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContentPanel.add(Box.createVerticalGlue());
        centerContentPanel.add(scoreLabel);
        centerContentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerContentPanel.add(teamButtonPanel);
        centerContentPanel.add(Box.createVerticalGlue());

        frame.setLayout(new BorderLayout());

        frame.add(centerContentPanel, BorderLayout.CENTER);

        frame.add(exitPanel, BorderLayout.SOUTH);
    }
    public void updateScoreDisplay(String scoreText) {
        scoreLabel.setText(scoreText);
    }

    private class PoengListener implements ActionListener {
        private Lag scoringLag;

        public PoengListener(Lag lag) {
            this.scoringLag = lag;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            padelKamp.registrerPoeng(scoringLag);

            if (padelKamp.erKampenVunnet()) {
                updateScoreDisplay("KAMP SLUTT! Vinner: " + padelKamp.getVinnerNavn());
                team1PointButton.setEnabled(false);
                team2PointButton.setEnabled(false);
            } else {
                updateScoreDisplay(padelKamp.hentHeleStillingen());
            }
        }
    }
}