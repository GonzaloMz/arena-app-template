import React from 'react';
import {useHistory} from "react-router-dom";
import Swal from 'sweetalert2';
export const shapeConfigurationMap = {
    appointment:{
        // entityRenderConfiguration:{
            allowInLineCreate:true,
            allowInLineEdit:true,
            // level:3,
            onCreateFinish:(e,history)=>{
                Swal.fire("Creación finalizada").then(()=>{history.push('/build/listContainer/view/appointment')});
                return null
            },
        list:{
            // shapeConfiguration:{
                onItemClick:  (item, history)=>{
                    history.push('/build/container/view/appointment/'+item);
                },
                fields:{
                    userId: {
                    // shapeConfiguration:{
    
                            visibility:{
                                visible:[ 'name','phone'],
                                hidden:[]
                            }
                        },
                    placeId: {
                        visibility:{
                            visible:['formattedAddress'],
                            hidden:[]
                        }
                    }
                },
                visibility:{
                    visible:['userId', 'placeId', 'estateType', 'operation'],
                    hidden:[]
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
