import React, { useEffect, useState } from 'react';
import ReactDatePicker, { registerLocale, setHours, setMinutes } from 'react-datepicker';
import { useHistory } from "react-router-dom";
import es from 'date-fns/locale/es';
import Autocomplete from "react-google-autocomplete";
import "react-datepicker/dist/react-datepicker.css";
import {EditablePhotoList, OperationSelector, PhotoEditor, ViewPriceRender} from './utils'

import Camera, { FACING_MODES, IMAGE_TYPES } from '@rinse/react-html5-camera-photo';
import '@rinse/react-html5-camera-photo/build/css/index.css';

import Carousel from 'react-elastic-carousel';
import {faCalendarAlt} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { DateDisplay } from './utils';
import {ArenaContainerMode} from 'frontend';
import { envConfig } from './config';
registerLocale('es', es)

const collapsableField = (className) =>({
    wrapperClassName: `${className} collapse`,
    titleWrapperProps:{
        "data-toggle":"collapse",
        "data-target":`.${className}.collapse`
    }
})

export const shapeConfigurationMap = {
    appointment: {
        entityRenderConfiguration: {
            allowInLineCreate: true,
            allowInLineEdit: true,
        },
        // level:3,
        onCreateFinish: (e, history) => {
            // Swal.fire("Creación finalizada").then(() => { history.push('/build/listContainer/view/appointment') });
            window.scrollTo(0, 0);
            
            history.push("/confirmation/appointment");
            return null
        },
        visibility: {
            visible: ['userId', 'estateType', 'placeId', 'operation', 'appointmentDate'],
            hidden: []

        },
        fields: {
            operation: {
                render: OperationSelector
            },
            appointmentDate: {
                render: (({ update, value, t, mode }) => {
                    // if (mode === 'VIEW') return <DateDisplay value={value} className='appointment-date'></DateDisplay>;
                    return <DatePickerCustom 
                        update={update}
                        value={value}
                        t={t}
                        mode={mode}></DatePickerCustom>
                })
            },
            placeId: {
                visibility: {
                    visible: ['formattedAddress', 'locality','floor', 'unit'],
                    hidden: []
                },
                fields: {
                    formattedAddress: {
                        // render: ({ update, mode, value, t, updateBuilder }) => {
                        //     if (mode === 'VIEW' || mode === 'EDIT') return false;

                        //     return <Place update={update} value={value} t={t} updateBuilder={updateBuilder}/>;
                        // }
                    }
                }
            }
        },
        list: {
            // shapeConfiguration:{
            onItemClick: (item, history) => {
                history.push('/build/container/view/appointment/' + item);
            },
            fields: {
                userId: {
                    // shapeConfiguration:{

                    visibility: {
                        visible: ['name', 'phone'],
                        hidden: []
                    }
                },
                placeId: {
                    visibility: {
                        hidden: ['floor', 'unit']
                    }
                }
            },
            visibility: {
                visible: ['userId', 'placeId', 'estateType', 'operation', 'appointmentDate'],
                hidden: []
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
    },
    assessmentCreation: {
        entityRenderConfiguration: {
            shapeConfiguration: {

                visibility: {
                    visible: ['estateType', 'operation', 'placeDescription', 'placeInventory', 'saleSuggestedValue'],
                    hidden: ['userId', 'placeId', 'estateType', 'operation', 'sugestedValue']
                },
                fields:{
                    // estateType:{
                    //     calculateMode: (mode)=>mode==='CREATE' ? 'VIEW' : mode
                    // },
                    // operation:{
                    //     calculateMode: (mode)=>mode==='CREATE' ? 'VIEW' : mode
                    // },
                    // placeDescription: collapsableField("place-description"),
                    // placeInventory:collapsableField("place-inventory"),
                    temporaryRentAssessment:collapsableField("temporary-rent-assessment"),
                    longRentAssessment:collapsableField("long-rent-assessment"),
                    saleSuggestedValue: collapsableField("sugested-value"),
                    placeInventory:{
                        visibility:{
                            visible: ['stove', 'oven', 'airExtractor', 'refrigerator', 'hotWaterTank', 'waterHeater', 'heaters', 'radiators', 'fans', 'airConditioners']
                        }
                    },
                    temporaryRentAssessment: {
                        ...collapsableField("temporary-rent-assessment"),
                        visibility:{
                            visible: ['temporaryRentFacilities', 'suggestedStayPrice', 'suggestedHalfStayPrice']
                        },
                        fields:{
                            temporaryRentFacilities:{
                                visibility:{
                                    visible:['numberOfOcupants', 'petFriendly'],
                                    // hidden:[]
                                }
                            }
                        }
                    }
                }
            },
            allowInLineCreate: true
        },
        onCreateFinish: (e, history) => {
            history.push('/confirmation/assessment');
            return null
        },
    },
    user: {
        entityRenderConfiguration: {
            allowInLineEdit: true,
            shapeConfiguration: {

            }
        },
        visibility: {
            visible: ['name', 'phone', 'email'],
            hidden: []
        }
    },
    assessment: {

        entityRenderConfiguration: {

            allowInLineCreate: true,
            allowInLineEdit: true,
        },
        fields: {
            operation: {
                render: OperationSelector
            },
            placeDescription: {
                visibility: {
                    visible: ['environments', 'toilets', 'squareMeterCovered', 'squareMeterTotal', 'carPlaces', 'coveredGarage', 'laundry', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection', 'extraNotes'],
                    hidden: []
                }
            }
        },
        list: {
            onItemClick: (item, history) => {
                history.push('/build/container/view/assessment/' + item + '?shapeName=assessmentDetail');
            },
            visibility: {
                visible: ['userId', 'estateType', 'operation', 'placeId', 'sugestedValue'],
                hidden: []
            },
            fields: {
                userId: {
                    visibility: {
                        visible: ['name', 'phone'],
                        hidden: []
                    }
                },
                placeId: {
                    visibility: {
                        visible: ['formattedAddress', 'locality'],
                        hidden: []
                    }
                },
                sugestedValue: {
                    render: ViewPriceRender
                }
            }
        }
    },
    place: {
        fields:{
            formattedAddress:{
                calculateMode :()=>'VIEW'          
            },
            locality: {
                calculateMode :()=>'VIEW'          
            }
        }
    },
    estateCreation: {
        entityRenderConfiguration: {
            allowInLineCreate: true,
            shapeConfiguration: {

                visibility: {
                    visible: ["owner", "placeId", "placeDescription", "estateType", "photos", "status", "operation", "speech", "temporaryRentFacilities", "temporaryRentPrice", "longRentPrice", "salePrice"],
                    hidden: ["price",]
                },
                fields: {
                    operation: {
                        render: OperationSelector
                    },
                    placeId: {

                        visibility: {
                            visible: ["formattedAddress", 'floor']
                        }
                    },
                    placeDescription: {
                        visibility: {
                            visible: ['environments', 'toilets', 'squareMeterCovered', 'squareMeterTotal', 'carPlaces', 'coveredGarage', 'laundry', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection', 'extraNotes']
                        }
                    },
                    estateType:{
                        calculateMode: (mode)=>mode==='CREATE' ? 'VIEW' : mode
                    },
                    operation:{
                        calculateMode: (mode)=>mode==='CREATE' ? 'VIEW' : mode
                    },
                    speech:{
                        render:({update, value, t, mode})=>{
                            if(mode!=='CREATE') return false;

                            return <div className='arena-field-mode-CREATE'>
                                    <div className='arena-test-list'>
                                        <div className='arena-text estate label'>{t('estate.speech.create.label')}</div>
                                    </div>
                                    <textarea onChange={(e)=>update(e.target.value)}></textarea>
                                </div>
                        }
                    },
                    placeInventory:{
                        calculateMode: (mode)=>mode==='CREATE' ? 'EDIT' : mode
                    },
                    temporaryRentPrice:{
                        visibility:{
                            visible: ["stayPrice", "halfStayPrice", "dailyPrice"],
                            hidden: [ "stayDailyPrice", "halfStayDailyPrice"]
                        },
                        render: ({entity})=>{
                            if(entity && entity.operation === 'RENT') return false;
                            return null;
                        }
                    },
                    salePrice: {
                        render: ({entity})=>{
                            if(entity && entity.operation === 'SALE') return false;
                            return null;
                        }
                    },
                    temporaryRentFacilities: {
                        render: ({entity})=>{
                            if(entity && entity.operation === 'RENT') return false;
                            return null;
                        }
                    },
                    longRentPrice:{
                        render: ({entity})=>{
                            if(entity && entity.operation === 'LONG_RENT') return false;
                            return null;
                        }
                    },
                    photos: {
                        list: {
                            listRender: EditablePhotoList
                        }
                    },
                }
            }
        },
        onCreateFinish: (e, history) => {
            history.push('/confirmation/estate');
            return null
        }
    },
    estate: {
        list: {
            visibility: {
                visible: ['placeId', 'estateType', 'operation', 'photos', 'placeDescription'],
                hidden: []
            },
            fields: {
                placeId: {
                    visibility: {
                        visible: ['formattedAddress'],
                        hidden: []
                    }
                },
                photos: {
                    list: {
                        onItemClick: () => alert("foto maximizada")
                    }
                },
                placeDescription: {
                    visibility: {
                        visible: ['environments', 'carPlaces', 'squareMeterTotal', 'toilets', 'laundry', 'squareMeterCovered', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection'],
                        hidden: []
                    }
                },
                operation: {
                    //render:({value, t})=>(<span>{`${t('operation.in')} ${value}`}</span>)
                }
            },
            onItemClick: (item, history) => {
                history.push('/build/container/view/estate/' + item);
            }
        },
        entityRenderConfiguration:{
            shapeConfiguration: {
                fields:{
                    photos:{
                        list:{
                            listRender: (items) => {
                                if (!items || items.length === 0) return null;
                                return <Carousel showTimeSelect={true} showArrows={true} itemsToShow={1} showEmptySlots={false}>{items.map(i => <img className='place-photo' src={i.view}></img>)}</Carousel>
                            }
                        }
                    }
                }
            },
            allowInLineEdit:true
        }
    },
    estateEdition: {
            entityRenderConfiguration:{
                allowInLineEdit:true,
                shapeConfiguration: {
                    fields:{
                        owner:{
                            ...collapsableField("estate-owner"),
                            calculateMode: ()=>(ArenaContainerMode.VIEW)
                        },
                        temporaryRentFacilities:{
                            ...collapsableField("estate-temporaryRentFacilities"),
                            calculateMode: ()=>(ArenaContainerMode.VIEW)
                        },
                        temporaryRentPrice:{
                            ...collapsableField("estate-temporaryRentPrice"),
                            calculateMode: ()=>(ArenaContainerMode.VIEW)
                        },
                        placeInventory:{
                            ...collapsableField("estate-placeInventory"),
                            calculateMode: ()=>(ArenaContainerMode.VIEW)
                        },
                        photos: {
                            list: {
                                listRender: EditablePhotoList
                            }
                        },
                    }
                }
            },
        },
    estateSearch: {
        visibility:{
            visible:['estateType', 'operation', 'environments', 'numberOfOcupants', 'price']
        },
        fields: {
            price: {
                render: ({entity = {}}) => entity.operation ? false : null
            },
            numberOfOcupants: {
                render: ({entity = {}}) => entity.operation && entity.operation === 'RENT' ? false : null
            },
            environments: {
                render: ({entity = {}}) => entity.operation && entity.operation === 'LONG_RENT' ? false : null
            }
        }
    },
    //     list: {
    //         visibility: {
    //             visible: ['estate'],
    //             hidden: []
    //         },
    //         fields: {
    //             estate: {
    //                 props: {
    //                     level: 2
    //                 },
    //                 visibility: {
    //                     visible: ['placeId', 'estateType', 'operation', 'photos', 'placeDescription'],
    //                     hidden: []
    //                 },
    //                 fields: {
    //                     placeId: {
    //                         visibility: {
    //                             visible: ['formattedAddress'],
    //                             hidden: []
    //                         }
    //                     },
    //                     placeDescription: {
    //                         visibility: {
    //                             //comentado para que funcione la búsqueda pública
    //                             // visible: ['environments', 'carPlaces', 'squareMeterTotal', 'toilets', 'laundry', 'squareMeterCovered', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection'],
    //                             hidden: []
    //                         }
    //                     }
                        
    //                 }
    //             }
    //         }
    //     },
    // },
    adminEstateSearch:{
        entityRenderConfiguration: {
            allowInLineCreate: true,
            shapeConfiguration: {
                list: {
                    visibility: {
                        visible: ['estate'],
                        hidden: []
                    },
                    fields: {
                        estate: {
                            props: {
                                level: 2
                            },
                            visibility: {
                                visible: ['placeId', 'estateType', 'operation', 'photos', 'placeDescription'],
                                hidden: []
                            },
                            fields: {
                                placeId: {
                                    visibility: {
                                        visible: ['formattedAddress'],
                                        hidden: []
                                    }
                                },
                                placeDescription: {
                                    visibility: {
                                        visible: ['environments', 'carPlaces', 'squareMeterTotal', 'toilets', 'laundry', 'squareMeterCovered', 'electricity', 'gas', 'waterNetworkConnection', 'sewerConnection'],
                                        hidden: []
                                    }
                                },
                                photos: {
                                    list: {
                                        listRender: (items) => {
                                            // if (!items || items.length === 0) return null;
                                            const itemsToShow = !items || items.length === 0 ? [{view:"/emptyPhoto.svg"}] : items;
                                            return <div className="adminEstateSearch-list-item">
                                                    <Carousel initialActiveIndex={0}showArrows={false}   itemsToShow={3} showEmptySlots={false}>{itemsToShow.map(i => <img className='place-photo' src={i.view}></img>)}</Carousel>
                                                </div>
                                        }
                                    }
                                }
                            }
                        }
                    },
                    onItemClick: (itemId, history, item) => {
                        console.log(item)
                        history.push(`/build/container/edit/estate/${item.estate}?shapeName=estateEdition`);
                    }
                },
                fields: {
                    operation: {
                        render: OperationSelector
                    }
                }
            }
        }
        
    },
    photo: {
        fields: {
            view: {
                render: (props) => <PhotoEditor {...props}></PhotoEditor>
                // {
                //     return (<div>
                //         {/* {
                //             !value &&
                //             <div>
                //                 <label class="custom-file-upload">

                //                     <input type="file" id="image" name="image" accept="image/*" capture={false} onChange={(e) => {
                //                         const imageFile = e.target.files[0];
                //                         console.log(imageFile);
                //                         let imageArguments, imageType, image, oldWidth, oldHeight, newHeight, canvas, ctx, newDataUrl, newWidth;

                //                         // Provide default values
                //                         imageType = imageType || "image/png";
                //                         imageArguments = imageArguments || 0.7;

                //                         // Create a temporary image so that we can compute the height of the downscaled image.
                //                         image = new Image();

                //                         // Create a temporary canvas to draw the downscaled image on.
                //                         canvas = document.createElement("canvas");

                //                         // Draw the downscaled image on the canvas and return the new data URL.
                //                         ctx = canvas.getContext("2d");

                //                         var reader = new FileReader();
                //                         reader.onloadend = function () {
                //                             console.log(reader.result)
                //                             image.onload = function () {
                //                                 oldWidth = image.width;
                //                                 oldHeight = image.height;
                //                                 newWidth = Math.min(350, image.width);
                //                                 newHeight = Math.floor(oldHeight / oldWidth * newWidth)
                //                                 canvas.width = newWidth;
                //                                 canvas.height = newHeight
                //                                 ctx.drawImage(image, 0, 0, newWidth, newHeight);
                //                                 newDataUrl = canvas.toDataURL(imageType, imageArguments);
                //                                 update(newDataUrl)
                //                             };
                //                             image.src = reader.result;
                //                         }
                //                         reader.readAsDataURL(imageFile);

                //                     }} />
                //                     <div className="btn btn-primary btn-block" data-trigger="fileinput">
                //                         {t("selectImageFromGallery", "Seleccionar de la galeria")}
                //                     </div>
                //                 </label>
                //                 <Camera
                //                     imageType={IMAGE_TYPES.JPG}
                //                     imageCompression={.5}
                //                     idealFacingMode={FACING_MODES.ENVIRONMENT}
                //                     onTakePhoto={(dataUri) => { update(dataUri); }}>
                //                 </Camera>
                //             </div>
                //         } */}
                //         { 
                //             !value && 
                //             <AvatarEditor
                //                 image="http://example.com/initialimage.jpg"
                //                 width={250}
                //                 height={250}
                //                 border={50}
                //                 color={[255, 255, 255, 0.6]} // RGBA
                //                 scale={1.2}
                //                 rotate={0}
                //             />
                //         }
                //         {
                //             value &&
                //             <img className='place-photo' src={value}></img>
                //         }
                //     </div>

                //     )
                // }
            }
        }
    },
    owner: {

        fields: {



            address: {
                visibility: {
                    visible: ['formattedAddress', 'floor']
                },
                fields: {
                    formattedAddress: {
                        render: ({ update, mode, value, t }) => {
                            if (mode === 'VIEW' || mode === 'EDIT') return false;

                            return <Place update={update} value={value} t={t}/>;
                        }
                    }
                }
            },




        }
    },
    administrator: {

        entityRenderConfiguration: {

            allowInLineCreate: true
        }
    },
    logIn: {

        entityRenderConfiguration: {

            allowInLineCreate: true
        },
        onCreateFinish: (e, history) => {
            // Swal.fire("Creación finalizada").then(() => {
            localStorage.setItem('arena-authorization', e.token); 
            localStorage.setItem('arena-administrator', e.administrator); 
            window.location.href='/';
            // });
            return null
        }
    },
    publicSearch: {
        entityRenderConfiguration: {
            allowInLineCreate: true,
            shapeConfiguration: {
                // visibility:{
                //     visible:['estateType', 'environments', 'operation', 'price']
                // },
                list: {
                    visibility: {
                        visible: ['estate'],
                        hidden: []
                    },
                    onItemClick: (itemId, history, item) => {
                        console.log(item)
                        history.push(`/build/container/view/estate/${item.estate}?shapeName=publicDetail`);
                    },
                    fields: {
                        estate: {
                            props: {
                                level: 2,
                            },
                            shapeConfiguration:{

                                visibility: {
                                    visible: ['price', 'photos', 'operation', 'estateType' , 'placeId', 'placeDescription'],
                                    hidden: []
                                },
                            },
                            fields: {
                                price:{
                                    render: ViewPriceRender
                                },
                                placeId: {
                                    shapeConfiguration:{

                                        visibility: {
                                            visible: ['locality'],
                                            hidden: []
                                        }
                                    }
                                },
                                photos: {
                                    beforeField: ({entity})=> (
                                        <div className="operation-indicator d-none">
                                            <div style={{width:'20%'}} className={`${entity.operation === 'SALE' ? 'bg-dark': 'bg-primary'}`}></div>
                                        </div>
                                    ),
                                    list: {
                                        listRender: (items) => {
                                            // if (!items || items.length === 0) return null;
                                            const itemsToShow = !items || items.length === 0 ? [{view:"/emptyPhoto.svg"}] : items;
                                            return <div>
                                                    <Carousel initialActiveIndex={0}showArrows={false}   itemsToShow={1} showEmptySlots={false}>{itemsToShow.map(i => <img className='place-photo' src={i.view}></img>)}</Carousel>
                                                </div>
                                        },
                                        onItemClick: () => alert("foto maximizada")
                                    }
                                },
                                placeDescription: {
                                    props:{
                                        level:10
                                    },
                                    shapeConfiguration:{
                                        visibility: {
                                            // visible: ['environments', 'toilets', 'carPlaces', 'squareMeterTotal'],
                                            hidden: []
                                        }
                                    }
                                },
                                operation:{
                                    render: ({ value="", update, t }) => {
                                        return <div className='operation-with-background'>
                                            <img className='operation-background position-absolute' style={{zIndex:-1}}src={`/frames/${value.toLowerCase()}.svg`}/>
                                            <div className="operation-value">    
                                                {t(`app.backend.model.enums.EstateOperations.${value}`)}
                                            </div>
                                        
                                        </div>
                                    }
                                }
                            }
                        }
                    }
                },
                fields: {
                    operation: {
                        render: OperationSelector
                    }
                }
            }
        }
    },
    publicDetail:{
        level: 10,
        entityRenderConfiguration: {
            shapeConfiguration: {
                visibility: {
                    visible: ['operation', 'placeId', 'photos', 'placeDescription', 'price', 'speech'],
                    hidden: []
                },
                fields:{
                    placeDescription: {
                        props:{
                            level:10
                        },
                        visibility: {
                            hidden: []
                        }
                    },
                    placeId: {
                        visibility:{
                            visible:['locality'],
                            hidden: []
                        }
                    },
                    photos: {
                        beforeField: ({entity})=> (
                            <div className="operation-indicator d-none">
                                <div style={{width:'20%'}} className={`${entity.operation === 'SALE' ? 'bg-dark': 'bg-primary'}`}></div>
                            </div>
                        ),
                        list: {
                            listRender: (items) => {
                                // if (!items || items.length === 0) return null;
                                const itemsToShow = !items || items.length === 0 ? [{view:"/emptyPhoto.svg"}] : items;
                                return <Carousel initialActiveIndex={0}showArrows={false}   itemsToShow={1} showEmptySlots={false}>{itemsToShow.map(i => <img className='place-photo' src={i.view}></img>)}</Carousel>
                            },
                            onItemClick: () => alert("foto maximizada")
                        }
                    },
                    price:{
                        render: (props)=>{
                            return <div>
                                <ViewPriceRender label={props.t('publicDetail.value')} {...props}></ViewPriceRender>
                            </div>
                        }
                    }
                }
            }
        }
    },
    rentCreation:{
        level:1,
        onCreateFinish: (e, history) => {
            // Swal.fire("Creación finalizada").then(() => { history.push('/build/listContainer/view/appointment') });
            window.scrollTo(0, 0);
            
            history.push("/confirmation/rent");
            return null
        },
        entityRenderConfiguration: {
            allowInLineCreate: true,
            shapeConfiguration:{
                visibility:{
                    visible:["tenant", "estate"],
                    hidden:[]
                },
                fields:{
                    owner:{
                        fields:{
                            user:{
                                props:{
        
                                    entityRenderConfiguration: {
                                        allowInLineCreate: true
                                    }
                                }
                            }
                        }
                    },
                    estate:{
                        visibility:{
                            visible:["estateType", "placeId", "temporaryRentFacilities", "price"],
                            hidden:[]
                        },
                        fields:{
                            placeId:{
                                props:{
                                    level:1
                                },
                                visibility:{
                                    visible:["locality", "formattedAddress"]
                                }
                            },
                            temporaryRentFacilities:{
                                props:{
                                    level:1
                                },
                                visibility:{
                                    visible:["numberOfOcupants"],
                                    hidden:[]
                                }
                            }
                        }
                    }
                }
            }
        },
    }
}

const Place = ({ update, value, t, updateBuilder }) => {
    const [formattedAddress, setFormattedAddress] = useState(value);
    const [selectedPlace, setSelectedPlace] = useState();
    useEffect(() => {
        update(formattedAddress);
        const locality=selectedPlace ? selectedPlace.address_components.find(c=>c.types.includes('locality')) : {long_name:''};
        // if(locality)
        updateBuilder('locality')(locality.long_name)
    }, [formattedAddress])
    const updatePlace = place => {
        console.log(place)
        setFormattedAddress(place.formatted_address)
        setSelectedPlace(place);
        
    }
    useEffect(() => {
        update(formattedAddress);
    }, [])
    const center = { lat: -36.613720, lng: -56.7048044 };
    // Create a bounding box with sides ~10km away from the center point
    const defaultBounds = {
        north: center.lat + 0.25,
        south: center.lat - 0.25,
        east: center.lng + 0.25,
        west: center.lng - 0.25
    };

    return <div className="autocomplete-field">
        <div>
            <div class="arena-text place formattedAddress label">
                {t("place.formattedAddress.label")}
            </div>
        </div>    
        <Autocomplete
            apiKey={'AIzaSyBYyI_5G4yLARo3fni9u2PBKePApgXhd5U'}
            onPlaceSelected={updatePlace}
            placeholder='Ingrese calle y altura'
            defaultValue={value}
            language='es_AR'
            options={{
                types: ["address"],
                componentRestrictions: { country: "ar" },
                bounds:defaultBounds
                // {
                //     north: -36.273299958870886,
                //     west: -56.615140226768425,
                //     south: -36.97482290301441,
                //     east: -56.870649405346185
                // }
                // {
                //     north: -34.969962,
                //     west: -56.36917670614324,
                //     south: -37.31999594951397,
                //     east: -56.298109887936505
                // }
                // ,
                // strictBounds:true
            }}
        />
    </div>
    ;
}

// export const DateDisplay = ({ value, className }) => {
//     if (!value) return <div className={className}>--/--</div>
//     const dateToDisplay = new Date(value);
//     const dd = String(dateToDisplay.getDate()).padStart(2, '0');
//     const mm = String(dateToDisplay.getMonth() + 1).padStart(2, '0'); //January is 0!
//     // var yyyy = dateToDisplay.getFullYear();

//     const formatted = dd + '/' + mm;

//     return <div className={className}>{formatted}</div>
// }

const DatePickerCustom = ({ update, value, t, mode }) => {
    const [selected, setSelected] =  useState(value ? new Date(value) : undefined);
    //if (mode === 'VIEW') return <DateDisplay value={value} className='appointment-date'></DateDisplay>;
    const filterPassedTime = (time) => {
        const currentDate = new Date();
        const selectedDate = new Date(time);
        if(selectedDate.getHours()<7 || selectedDate.getHours()>21)
            return false;

        return currentDate.getTime() < selectedDate.getTime();
    };

    return (
        <div>
            <div class="date-input-label subtitle">{t('appointment.appointmentDate.label')}</div>
            <DateDisplay value={value} className='appointment-date'></DateDisplay>
            <div className='date-picker'>
                <div className='d-inline'>
                    <FontAwesomeIcon icon={faCalendarAlt} className='pt-2 gray-icon' size='2x'></FontAwesomeIcon>
                </div>
                <ReactDatePicker
                    className="arena-edit-field"
                    selected={selected}
                    onChange={(date) => {
                        const hours= new Date(date).getHours();
                        if(hours>=7 && hours<=21)
                        update(date)
                        setSelected(date)
                    }}
                    showTimeSelect
                    timeCaption='Hora'
                    filterTime={filterPassedTime}
                    locale="es"
                    onChangeRaw={(e) => { e.preventDefault() }}
                    dateFormat="MMMM d, yyyy HH:mm"
                    onFocus={(e) => e.target.readOnly = true}
                    />
            </div>
        </div>
    );
}