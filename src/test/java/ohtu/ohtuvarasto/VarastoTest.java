package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void vaaraAlustaminenAsettaaTilavuudeksiNollan() {
        Varasto varasto2 = new Varasto(-5);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void huonotParametritAsettavatNollatSaldoonJaTilavuuteen() {
        Varasto tempVarasto = new Varasto(-5, -5);
        assertEquals(0.0, tempVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toimiiKunnollisillaParametreilla() {
        Varasto tempVarasto = new Varasto(5, 5);
        assertEquals(5.0, tempVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5.0, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yliMaarainenSaldoMeneeHukkaan() {
        Varasto tempVarasto = new Varasto(5, 7);
        assertEquals(5.0, tempVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5.0, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaaminenPalauttaaNull() {
        Varasto tempVarasto = new Varasto(10, 7);
        tempVarasto.lisaaVarastoon(-5.0);
        assertEquals(7.0, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoMeneeTayteenKunLisaaLiikaa() {
        Varasto tempVarasto = new Varasto(10, 7);
        tempVarasto.lisaaVarastoon(12.00);
        assertEquals(10.00, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldolleEiTapahduMitaanNegatiivisellaParametrilla() {
        Varasto tempVarasto = new Varasto(10, 7);
        tempVarasto.otaVarastosta(-5.0);
        assertEquals(7.0, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josOttaaYliSaldonSaldoTyhjenee() {
        Varasto tempVarasto = new Varasto(10, 7);
        tempVarasto.otaVarastosta(2000.0);
        assertEquals(0.0, tempVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void printtaaTilanOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }


}