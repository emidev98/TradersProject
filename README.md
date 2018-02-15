
Volem fer una aplicació que es connecti amb la base de dades `traders` i que permeti la creació de nous mercaders.

# Vistes

En aquest apartat farem les vistes i els _beans_ que les recolzen. Les dades
encara no es llegiran de la base de dades, sinó que estaran codificades
directament en el codi.

Les vistes que es necessitaran són les següents:

- Creació d'un mercader: podrem introduir les dades d'un nou mercader (nom,cognom i malnom). Introduirem també la data en què el mercader comença la seva activitat (una data entre el 2200 i el 2500).
- Elecció d'un planeta: podrem seleccionar un sistema i un dels planetes del sistema. Aquesta vista s'utilitzarà tant per triar la posició inicial d'un mercader com per triar cadascun dels destins posteriors.
- Adquisició d'una nau: podrem seleccionar una de les naus que estigui disponible (que no tingui propietari) en una certa data i adquirir-la. Hem de poder triar la nau, la forma com l'hem adquirida, i el preu que ens ha costat. Aquesta vista s'utilitzarà per assignar una primera nau al mercader i, posteriorment, cada cop que es vulgui adquirir una nova nau.
- Consulta de preus: a partir d'una data concreta, hem de poder veure el preu de qualsevol mercaderia a qualsevol planeta. Aquesta vista s'utilitzarà per ajudar a decidir a quin planeta es vol viatjar.

# Lògica del programa

- Creació d'un mercader.

- Viatges.

- Adquisició de naus.

- Compravenda de mercaderies.

- Pèrdua de naus.

- Retirada d'un mercader.