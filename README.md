<h1>🌦️ Weather App</h1>

<p>
Une application mobile pour la platforme <b>Android</b> qui permet de consulter la météo en fonction 
de la géolocalisation (précise ou approximative) de l'utilisateur ou en fonction d'une ville.
</p>

<br />

<h2>🤖 Téchnologies utilsées</h2>
<ul>
<li><b>Platforme :</b> Android</li>
<li><b>Android API Min Sdk :</b> 24</li>
<li><b>Android API Max Sdk (target sdk) :</b> 36</li>
<li><b>Langage de programmation :</b> Java (jdk 11)</li>
<li><b>Construction de l'Interface utilisateur via :</b> XML</li>
<li><b>API de météo en temps réel :</b> OpenWeatherAPI</li>
</ul>

<br />

<h2>🔌 Dépendences utiles</h2>
<ul>
<li><b>swiperefreshlayout :</b> pour gérer facilement le rafraichissement de la page</li>
<li><b>retrofit :</b> sert à communiquer avec des API web (REST) de manière simple, rapide et propre</li>
<li><b>converter-gson :</b> permet de convertir automatiquement les réponses JSON en objets Java</li>
<li><b>picasso :</b> permet de charger une image depuis une URL dans une ImageView en une seule ligne de code</li>
</ul>


<br />

<h2>🎯 Démostration</h2>

<h4>✅ Lancement de l'application</h4>
<img src="./imgs/launchApp.png" alt="Lancement de l'application" />
<p>
Affichage de la vue principale activity_main, 
qui permet à l'utilisateur de consulter les données météorologiques 
d'une ville, ou en fonction de sa localisation 
</p>

<br />
<h4>
✅ Tentative de consultation des données météorologiques en fonction de la localisation de l'utilisateur
</h4>
<img src="./imgs/screen2.png" alt="image 2" />
<p>
L'utilisateur doit permettre à l'application d'accéder à sa localisation
</p>

<br />
<h4>
✅ L'utilisateur clique sur "Don't Allow" (ne n'autorise pas l'accès à sa localisation)
</h4>
<img src="./imgs/screen3.png" alt="image 3" />
<p>
L'application affiche un bottom_sheet_dialog layout qui explique à l'utilisateur que l'accès à sa localisation
 est necessaire pour qu'il consulte la météo
</p>

<br />
<h4>
✅ L'utilisateur autorise l'accès à sa localisation
</h4>
<img src="./imgs/screen4.png" alt="image 4" />
<p>
L'application affiche un messag box qui permet à l'utilisateur d'accéder aux paramètres pour activer la 
localisation 
</p>

<br />
<h4>
✅ L'utilisateur autorise l'accès à sa localisation
</h4>
<img src="./imgs/screen5.png" alt="image 5" />
<p>
L'utilisateur accède aux paramètres de la "Localisation" pour autoriser l'accès
</p>

<br />
<h4>
✅ L'utilisateur peut maintenant consulter la météo en fonction de sa localisation
</h4>
<img src="./imgs/screen6.png" alt="image 6" />
<p>
Récupération des données météorologique depuis l'API OpenWeatherAPI
</p>
<br />
<img src="./imgs/rabat.png" alt="météo rabat" />
<p>
Affichage des données météorologique dans la vue activity_weather.xml
</p>

<br />
<h4>
✅ Données météorologiques d'une ville
</h4>
<img src="./imgs/screen11.png" alt="image 11" />
<p>
L'utilisateur clique sur le bouton "Get Weather by City Name" pour consulter les données météorologique 
d'une ville
</p>

<br />
<h4>
✅ L'utilisateur saisit une ville et clique sur l'icon "loupe"
</h4>
<img src="./imgs/screen12.png" alt="image 12" />
<p>
Récupération des données météorologique de la ville "NewYork" depuis l'API OpenWeatherAPI
</p>

<br />
<h4>
✅ Message d'erreur
</h4>
<img src="./imgs/screen13.png" alt="image 13" />
<p>
L'utilisateur clique sur rechercher (l'icon loupe) sans saisir le nom d'une ville 
</p>

<br />
<h4>
✅ Message d'erreur
</h4>
<img src="./imgs/notfound.png" alt="image 14" />
<p>
L'API OpenWeather ne supporte pas le nom de cette ville ou nom de ville incorrecte
</p>

<br />
<hr />
<br />
<ul>
<li>💻 <b>Réalisé par :</b> Iliasse Abdessamad</li>
<li>📚 <b>Sujet :</b> Developpement Mobile Natif pour la platforme Android</li>
<li>📅 <b>Année : </b> 2024 - 2025</li>
</ul>
