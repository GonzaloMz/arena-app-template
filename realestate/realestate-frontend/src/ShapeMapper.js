import React from 'react';
import {useHistory} from "react-router-dom";
import Swal from 'sweetalert2';
export const shapeConfigurationMap = {
    appointment:{
        // entityRenderConfiguration:{
            allowInLineCreate:true,
            onCreateFinish:(e,history)=>{
                Swal.fire("Creación finalizada").then(()=>{history.push('/build/listContainer/view/appointment')});
                return null
            },
        list:{
            // shapeConfiguration:{

                fields:{
                    userId: {
                    // shapeConfiguration:{
    
                            visibility:{
                                visible:[ 'phone'],
                                hidden:[]
                            }
                        }
                }
            }
        // }

        // ,fields:{
            // userId: {
                // visibility:{
                    // hidden:['name','lastName']
                // }
            // }
        // }
    // }
    }
}
