package waisol.tech.mominholyquranqibladirectionnamesofallahprayertimingstasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TalwatNextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talwat_next);
    }

    public void a_next(View view) {
        startActivity(new Intent(this, aWeb.class));
    }

    public void b_next(View view) {
        startActivity(new Intent(this, bWeb.class));
    }

    public void c_next(View view) {
        startActivity(new Intent(this, cWeb.class));
    }

    public void d_next(View view) {
        startActivity(new Intent(this, dWeb.class));
    }

    public void e_next(View view) {
        startActivity(new Intent(this, eWeb.class));
    }

    public void f_next(View view) {
        startActivity(new Intent(this, fWeb.class));
    }

    public void g_next(View view) {
        startActivity(new Intent(this, gWeb.class));
    }

    public void h_next(View view) {
        startActivity(new Intent(this, hWeb.class));
    }

    public void i_next(View view) {
        startActivity(new Intent(this, iWeb.class));
    }

    public void j_next(View view) {
        startActivity(new Intent(this, jWeb.class));
    }

    public void k_next(View view) {
        startActivity(new Intent(this, kWeb.class));
    }

    public void l_next(View view) {
        startActivity(new Intent(this, lWeb.class));
    }

    public void m_next(View view) {
        startActivity(new Intent(this, mWeb.class));
    }

    public void n_next(View view) {
        startActivity(new Intent(this, nWeb.class));
    }

    public void o_next(View view) {
        startActivity(new Intent(this, oWeb.class));
    }

    public void p_next(View view) {
        startActivity(new Intent(this, pWeb.class));
    }

    public void q_next(View view) {
        startActivity(new Intent(this, qWeb.class));
    }

    public void back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
