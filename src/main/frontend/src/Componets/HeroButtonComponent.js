import React, {useState} from "react";
import axios from 'axios';






// creates a new hero that is able to be clicked by the user. Once a hero is clicked the draft table will be updated.
// As the user clicks heroes, the getHeroMatchupData service will be called as well
export default function HeroButtonComponent(heroName)
{
    //const currentHeroImage = React.createContext();


    return (

            <img src={"/heroes/" + heroName + ".png"} style={{height: "50 px", width: "50 px"}} //onClick={

                /*
                <currentHeroImage.Provider
                    value ={"/heroes/" + heroName + ".png"}>
                </currentHeroImage.Provider>

                 TODO axios calls and cross origin resources sharing on click

            } */
            />
    )
}


