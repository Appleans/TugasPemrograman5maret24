import javax.swing.*;

public class SimpleQuizUI extends JFrame {
    // Deklarasi variabel UI
    private JLabel questionLabel;
    private JRadioButton[] options; // Array untuk menyimpan opsi jawaban
    private JButton nextButton;
    private int currentQuestionIndex = 0;
    private String[][] questions = { // Array untuk menyimpan pertanyaan dan jawaban
            {"Apa ibukota Indonesia?", "Jakarta", "Surabaya", "Bandung", "a"},
            {"Berapa jumlah provinsi di Indonesia?", "30", "38", "40", "b"},
            {"Apa warna bendera Indonesia?", "Merah-Putih", "Merah-Hitam", "Hijau-Putih", "a"}
    };
    private int score = 0;

    public SimpleQuizUI() {
        // Konfigurasi frame
        setTitle("Kuis Sederhana");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Inisialisasi komponen UI
        questionLabel = new JLabel();
        add(questionLabel);

        options = new JRadioButton[3]; // Menginisialisasi array opsi jawaban
        ButtonGroup optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]); // Menambahkan opsi ke grup agar hanya satu yang dapat dipilih
            add(options[i]); // Menambahkan opsi ke panel
        }

        nextButton = new JButton("Selanjutnya");
        add(nextButton);

        nextButton.addActionListener(e -> {
            checkAnswer(); // Memeriksa jawaban saat tombol "Selanjutnya" ditekan
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                updateQuestion(); // Memperbarui pertanyaan jika masih ada pertanyaan yang belum dijawab
            } else {
                showResult(); // Menampilkan skor akhir jika semua pertanyaan telah dijawab
            }
        });

        updateQuestion(); // Memperbarui pertanyaan pertama kali saat aplikasi dimulai
    }

    private void checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected() && options[i].getActionCommand().equals(questions[currentQuestionIndex][4])) {
                score++; // Menambahkan skor jika opsi yang dipilih benar
                break; // Keluar dari loop karena hanya satu opsi yang dapat dipilih
            }
        }
    }

    private void updateQuestion() {
        questionLabel.setText(questions[currentQuestionIndex][0]); // Mengatur teks pertanyaan
        for (int i = 0; i < options.length; i++) {
            options[i].setText(questions[currentQuestionIndex][i + 1]); // Mengatur teks opsi jawaban
            options[i].setActionCommand(Character.toString((char) ('a' + i))); // Menetapkan aksi perintah sebagai 'a', 'b', 'c'
            options[i].setSelected(false); // Menghapus pilihan sebelumnya
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Skor Akhir Anda: " + score + "/3"); // Menampilkan skor akhir
        if (score == 3) {
            JOptionPane.showMessageDialog(this, "Selamat! Anda adalah seorang yang paham tentang Indonesia!"); // Menampilkan pesan selamat jika skor maksimal
        } else if (score >= 1) {
            JOptionPane.showMessageDialog(this, "Anda telah menjawab sebagian besar dengan benar. Terus belajar!"); // Menampilkan pesan motivasi jika skor minimal satu
        } else {
            JOptionPane.showMessageDialog(this, "Tampaknya Anda perlu mempelajari lebih banyak tentang Indonesia."); // Menampilkan pesan motivasi jika skor nol
        }
        dispose(); // Menutup frame setelah menampilkan skor
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleQuizUI quizUI = new SimpleQuizUI();
            quizUI.setVisible(true); // Menampilkan frame aplikasi
        });
    }
}
