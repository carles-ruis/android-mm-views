package com.carles.mm.fakes

val MONSTERS_RESPONSE_DTO = """
    {
  "data": [
    {
      "category": "monsters",
      "common_locations": null,
      "description": "These Lizalfos have been influenced by Ganon's fiendish magic to become the strongest Lizalfos of all. They're called \"silver\" for their unique coloring and also to denote their rarity. Their purple pattern makes them even more distinct.",
      "drops": [
        "lizalfos horn",
        "lizalfos talon",
        "lizalfos tail",
        "amber",
        "opal",
        "topaz",
        "ruby",
        "sapphire",
        "diamond"
      ],
      "id": 120,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/silver_lizalfos/image",
      "name": "silver lizalfos"
    },
    {
      "category": "monsters",
      "common_locations": null,
      "description": "An ancient civilization originally designed Guardians to combat Ganon but then scaled them down to place in shrines as part of the trials. This model is very resilient and has been outfitted with triple-blade functionality, allowing it to wield three weapons. This will put any would-be hero to the test for sure. A great deal of strength and confidence alike are required to contend with one of these.",
      "drops": [
        "ancient screw",
        "ancient spring",
        "ancient gear",
        "ancient shaft",
        "ancient core"
      ],
      "id": 133,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/guardian_scout_iv/image",
      "name": "guardian scout iv"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Gerudo Highlands",
        "Hebra Mountains"
      ],
      "description": "These quick-witted, lizard-like monsters can be found all over Hyrule. The balls of ice they spit make them particularly troublesome, but exposure to fire will kill them instantly. Their homes are mainly on snowy mountains.",
      "drops": [
        "lizalfos horn",
        "lizalfos talon",
        "icy lizalfos tail"
      ],
      "id": 118,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/ice-breath_lizalfos/image",
      "name": "ice-breath lizalfos"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Hyrule Field",
        "Tabantha Frontier"
      ],
      "description": "This enormous monster is naturally camouflaged as a rock formation. Neither stone nor arrow can pierce its stony form, but a cunning adventurer knows to scale its body and attack the ore sprouting from its peak. Unlike your average Stone Talus, this type's ore deposit contains a large amount of precious ore.",
      "drops": [
        "flint",
        "amber",
        "opal",
        "topaz",
        "ruby",
        "sapphire",
        "diamond"
      ],
      "id": 140,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/stone_talus_(rare)/image",
      "name": "stone talus (rare)"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Lanayru Great Spring",
        "Gerudo Desert"
      ],
      "description": "These quick-witted, lizard-like monsters can be found all over Hyrule. They're a sly species that lurks underwater or uses camouflage to blend in with the environment to launch ambushes. Moreover, they never sleep. They're meat eaters by nature but will enjoy the occasional insect or two.",
      "drops": [
        "lizalfos horn",
        "lizalfos talon"
      ],
      "id": 113,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/lizalfos/image",
      "name": "lizalfos"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Gerudo Desert",
        "Hyrule Ridge"
      ],
      "description": "The remains of Lizalfos appear in the dark of night. They're as sly as ever, even now that they're just some bones stacked atop each other. However, their bodies are much more fragile, and a single solid hit can reduce them to pieces. If a skull remains intact, they will pull themselves back up and continue to fight. They've been known to grab the wrong skull at times, but they never seem to mind...",
      "drops": [
        "lizalfos horn",
        "lizalfos talon"
      ],
      "id": 116,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/stalizalfos/image",
      "name": "stalizalfos"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Hebra Mountains",
        "Gerudo Highlands"
      ],
      "description": "This enormous monster is naturally camouflaged as a frozen rock formation. Neither sword nor arrow can pierce its frigid form. Merely touching its frosty body can leave you with severe frostbite, but you may be able to scale it if you use something to thaw its icy exterior.",
      "drops": [
        "flint",
        "opal",
        "sapphire",
        "diamond"
      ],
      "id": 142,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/frost_talus/image",
      "name": "frost talus"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Greater Hyrule"
      ],
      "description": "A very young Stone Talus. Their bodies toughen as they mature, becoming as tough as boulders by adulthood. As a child, however, their bodies are light enough to be lifted and fragile enough to break when thrown.",
      "drops": [
        "flint",
        "amber",
        "opal"
      ],
      "id": 143,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/stone_pebblit/image",
      "name": "stone pebblit"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Hyrule Field",
        "Hebra Mountains"
      ],
      "description": "These spell-casting monsters can be found all over Hyrule. They use their blizzard rods to create freezing blasts of air or to summon frozen monsters and have been known to cause blizzards to severely decrease the temperature around them. The weather will normalize once the Blizzrobe is defeated.",
      "drops": [],
      "id": 101,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/blizzrobe/image",
      "name": "blizzrobe"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Eldin Canyon",
        "Eldin Mountains"
      ],
      "description": "The fire that engulfs the bodies of these bat-like Keese makes them more dangerous than the standard type. They're capable of setting fire to anything they touch.",
      "drops": [
        "fire keese wing",
        "keese eyeball"
      ],
      "id": 89,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/fire_keese/image",
      "name": "fire keese"
    },
    {
      "category": "monsters",
      "common_locations": null,
      "description": "You would be foolish to call these Silver Bokoblins a mere nuisance. They have been influenced by Ganon's fiendish magic, so they are stronger than even the Black Bokoblins. They're called \"silver\" not only because of their coloring but also to denote their rarity. The purple markings help them to stand out even more.",
      "drops": [
        "bokoblin horn",
        "bokoblin fang",
        "bokoblin guts",
        "amber",
        "opal",
        "topaz",
        "ruby",
        "sapphire",
        "diamond"
      ],
      "id": 107,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/silver_bokoblin/image",
      "name": "silver bokoblin"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Lanayru Great Spring",
        "Hyrule Field"
      ],
      "description": "These fearsome monsters have lived in Hyrule since ancient times. They possess intense intelligence, resilience, and strength, making them among the most dangerous monsters in all the land. This is compounded by the fact that they have a natural resistance to all elements. You would be wise to challenge a Lynel only if you're very well prepared.",
      "drops": [
        "lynel horn",
        "lynel hoof",
        "lynel guts"
      ],
      "id": 121,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/lynel/image",
      "name": "lynel"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Death Mountain"
      ],
      "description": "These sentries dispatched from Divine Beast Vah Rudania are equipped with searchlights that can spot intruders.",
      "drops": [
        "ancient screw",
        "ancient spring",
        "ancient gear",
        "ancient shaft",
        "ancient core"
      ],
      "id": 128,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/sentry/image",
      "name": "sentry"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Hyrule Field",
        "Gerudo Highlands"
      ],
      "description": "Although Bokoblins are generally a nuisance, the Black Bokoblins are among the most dangerous type. They're extremely resilient, and many are armed with more advanced weapons.",
      "drops": [
        "bokoblin horn",
        "bokoblin fang",
        "bokoblin guts"
      ],
      "id": 105,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/black_bokoblin/image",
      "name": "black bokoblin"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Gerudo Desert"
      ],
      "description": "This massive monster swims beneath the desert's sand. It spends most of its time submerged, but if it senses sound, it will breach the surface to feast on whatever it can grab. Running around carelessly can be dangerous if you suspect there may be one in the area.",
      "drops": [
        "molduga fin",
        "molduga guts"
      ],
      "id": 151,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/molduga/image",
      "name": "molduga"
    },
    {
      "category": "monsters",
      "common_locations": [
        "Divine Beast Vah Naboris"
      ],
      "description": "This phantom of Ganon attacked the Divine Beast Vah Naboris and was responsible for the demise of the Champion Urbosa. It specializes in quick, lightning-based attacks.",
      "drops": [],
      "id": 159,
      "image": "https://botw-compendium.herokuapp.com/api/v2/entry/thunderblight_ganon/image",
      "name": "thunderblight ganon"
    }
  ]
}
""".trimIndent()
    