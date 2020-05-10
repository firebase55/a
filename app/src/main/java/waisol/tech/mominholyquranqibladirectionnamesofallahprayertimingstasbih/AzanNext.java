package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AzanNext extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azan_next);
    }

    public void a_azan_next(View view) {
        startActivity(new Intent(this, OneAzanPlayer.class));
    }

    public void b_azan_next(View view) {
        startActivity(new Intent(this, TwoAzanPlayer.class));
    }

    public void c_azan_next(View view) {

        startActivity(new Intent(this, ThreeAzanPlayer.class));
    }

    public void d_azan_next(View view) {

        startActivity(new Intent(this, FourAzanPlayer.class));
    }

    public void e_azan_next(View view) {

        startActivity(new Intent(this, FiveAzanPlayer.class));
    }

    public void f_azan_next(View view) {

        startActivity(new Intent(this, SixAzanPlayer.class));
    }

    public void g_azan_next(View view) {

        startActivity(new Intent(this, SevenAzanPlayer.class));
    }

    public void h_azan_next(View view) {

        startActivity(new Intent(this, EightAzanPlayer.class));
    }

    public void i_azan_next(View view) {

        startActivity(new Intent(this, NineAzanPlayer.class));
    }

    public void j_azan_next(View view) {

        startActivity(new Intent(this, TenAzanPlayer.class));
    }

    public void k_azan_next(View view) {

        startActivity(new Intent(this, ElevenAzanPlayer.class));
    }

    public void l_azan_next(View view) {

        startActivity(new Intent(this, TwelveAzanPlayer.class));
    }

    public void m_azan_next(View view) {

        startActivity(new Intent(this, ThirteenAzanPlayer.class));
    }

    public void n_azan_next(View view) {

        startActivity(new Intent(this, FourteenAzanPlayer.class));
    }

    public void o_azan_next(View view) {

        startActivity(new Intent(this, FifteenAzanPlayer.class));
    }

    public void p_azan_next(View view) {

        startActivity(new Intent(this, SixteenAzanPlayer.class));
    }

    public void q_azan_next(View view) {

        startActivity(new Intent(this, SeventeenAzanPlayer.class));
    }

    public void r_azan_next(View view) {

        startActivity(new Intent(this, EighteenAzanPlayer.class));
    }

    public void s_azan_next(View view) {

        startActivity(new Intent(this, NineteenAzanPlayer.class));
    }

    public void t_azan_next(View view) {

        startActivity(new Intent(this, TwentyAzanPlayer.class));
    }

    public void back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
