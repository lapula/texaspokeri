<p>
<b>Huomio luokkakaaviosta:</b> koostuu nuoli sisälsi siinä UML editorissa jolla tein sen "salmiakkikuvion" toisessa päässä ison nuolen, toivottavasti se ei aiheuta liikaa hämmennystä.</p>

<h3>
Käynnistys:
<h3>
<p>
Ohjelma luo asetus-sliderien pohjalta pelin sekä peli-ikkunan klikattaessa start asetusikkunassa.
Peli (Game) luokka taas luo suurimman osan muista luokista.
</p>

<h3>
Pelaaminen:
<h3>
<p>
Painaessa start peli-ikkunassa peli kutsuu Game-luokan startRound() metodia, josta päädytään kutsumaan Bidding-
luokan metodeja, josta päästään lopulta takaisin Game luokan startRoundiin. Tätä kierota jatkuu kunnes kierrosmäärä
tulee täyteen (tai muu tapahtuma joka lopettaa pelin aiemmin). Lopulta Game luo Resolve-luokan, joka puolestaan luo
HandRating-luokan, jotka palauttavat Game-luokalle pelin voittajan.
</p>

<h3>
Muiden luokkien kuvauksia:
<h3>
<p>
<div><b>Kortit:</b></div><br>
</p>
<p>
Kortteja simuloivat luokat Suit, Card (ja Deck). Suit on enum joka kuvaa maan nimeä ja numeroa. Card taas koostuu
kortin numerosta ja Suit:ista. Kortit luodaan Deck-luokassa, joka luodaan Game-luokassa pelin alkaessa.
</p>
<p>
<div><b>Pöytä:</b></div><br>
</p>
<p>
Table luokka pitää kirjaa pelipöydän tapahtumista. Sekin luodaan Game-luokassa. Table koostuu korteista ja potin
määrästä (integer).
</p>
<p>
<div><b>Pelaajat:</b></div><br>
</p>
<p>
Pelaajia simuloi Player-luokka. Sillä on mm. rahamäärä, kortit ja boolean joka kertoo onko ihminen vai AI. 
Pelaajat laitetaan AllPlayers-luokkaan ArrayListiin, jotta voidaan tehdä ero pelaavien ja kierroksella mukana
olevien pelaajien välille. GameSettings luo nämä molemmat.
</p>
