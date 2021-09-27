const EstateTypeTranslations = {
    CASA: 'Casa',
    DEPARTAMENTO: 'Departamento',
    LOTE: 'Lote',
    LOCAL: 'Local',
    DUPLEX: 'Duplex',
    TRIPLEX: 'Triplex',
    COCHERA: 'Cochera',
    GALPON: 'Galpón',
    null: 'No definido'
}

export const textMap = {

    es: {
        clear: "Nueva búsqueda",
        buttons:{
            search: "Buscar"
        },
        errors:{
            // required: "Requerido",
            title: "⚠ Revisá estos errores para poder continuar",
            user: {
                email: {
                    invalid: "El email ingresado es inválido",
                    required: "El email del usuario es requerido"
                },
                name:{
                    required: "El nombre de usuario es requerido"
                }
            },
            appointment: {
                place: {
                    unit:{
                        required: "El número de departamento es requerido para el tipo de propiedad Departamento"
                    },
                    required: 'Debes ingresar una "dirección"'
                },
                user:{
                    required: 'Debes ingresar un "usuario"'
                },
                estateType: {
                    required: 'Debes ingresar un "tipo de propiedad"'
                },
                operation:{
                    required: 'Debes ingresar un "tipo de operación"'
                }
            },
            place: {
                formattedAddress: {
                    required: 'Debes ingresar una "dirección"'
                }
            },
            assessment:{
                sugestedValue:{
                    required: 'Debes ingresar un "precio sugerido".'
                }
            },
            estate:{
                price:{
                    required: 'Debes ingresar un "precio".'
                },
                status:{
                    required: 'Debes ingresar el "estado de la publicación"'
                }
            },
            owner:{
                dni:{
                    required: 'Debes ingresar el dni del cliente propietario.'
                },
                cuit:{
                    required: 'Debes ingresar el cuit del cliente propietario.'
                },

            }
        },
        confirmation:{
            home: 'Ir al Inicio',
            appointment:{
                title:"La Cita fue agendada con éxito!",
                subtitle:"Podrás verla en las Tasaciones Pendientes.",
                next: "Ir a Tasaciones Pendientes"
            },
            assessment:{
                title:"La Tasación fue agregada con éxito!",
                subtitle:"Podrás verla en las Tasaciones Realizadas.",
                next: "Ir a Tasaciones Realizadas"
            },
            estate:{
                title:"La Propiedad fue agregada con éxito!",
                subtitle:"Podrás verla en las Propiedades Disponibles.",
                next: "Ir a Propiedades Disponibles"
            }
        },
        list: {
            appointment: {
                label: "Tasaciones",
                sublabel: "Pendientes"
            },
            assessment: {
                label: "Tasaciones",
                sublabel: "Realizadas"
            }
        },
        publicSearch:{
            placeDescription:{
                environments:"Ambientes", 
                toilets:"Baños", 
                carPlaces:"Vehículos"//, 'squareMeterTotal': 'm2'
            }
        },
        estate: {
            create: {
                finish: "Finalizar alta de propiedad"
            }
        },
        photo: {
            create: {
                start: "Agregar foto",
                finish: "Listo",
                label: "Tome una nueva fotografía de este lugar"
            }
        },
        appointment: {
            create: {
                start: "Nueva Tasación",
                finish: "Guardar",
                label: "Nueva Tasación"
            },
            operation: {
                placeholder: "Operación",
                label: "Tipo de operación"
            },
            estateType: {
                label: "Tipo de propiedad"
            },
            placeId: {

                label: "Ubicación de la propiedad"
            },
            userId: {
                label: "Interesado"
            },
            appointmentDate: {
                label: "Agendar cita"
            },
            view:{
                label: "Tasación",
                sublabel: "Pendiente"
            }
        },
        user: {
            create: {
                start: "Crear Nuevo Usuario"
            },
            search: {
                inline: {
                    placeholder: "Búsqueda por nombre, email o teléfono"
                }
            },
            lastName: {
                label: "Apellido",
                placeholder: "Ingrese el apellido de la persona"
            },
            name: {
                label: "Nombre",
                placeholder: "Ingrese el nombre y apellido de la persona"
            },
            phone: {
                label: "Teléfono",
                placeholder: "Ingrese el teléfono de la persona"
            },
            email: {
                label: "Email",
                placeholder: "Ingrese el email de la persona"
            }
        },
        place: {
            unit: {
                label: "Departamento",
                placeholder: "Ingrese la unidad "
            },
            floor: {
                label: "Piso",
                placeholder: "Ingrese el piso"
            },
            formattedAddress: {
                label: "Dirección",
                placeholder: "Ingrese la numeración seguida de la calle"
            },
            locality:{
                label: "Localidad",
                placeholder: "Localidad seleccionada"
            }
        },
        assessment: {
            create: {
                start: "Iniciar Tasación",
                finish: "Cerrar Tasación"
            },
            view:{
                label: "Tasación",
                sublabel:"Realizada"
            },
            operation: {
                placeholder: "Tipo de operación",
                label: "Tipo de operación"
            },
            placeId: {
                label: "Ubicación de la propiedad"
            },
            userId: {
                label: "Interesado"
            },
            estateType: {
                label: "Tipo de propiedad"
            },
            sugestedValue: {
                label: "Valor sugerido",
                placeholder: "Ingrese el valor sugerido en U$D para este inmueble"
            },
            placeDescription: {
                label: "Descripción de la propiedad"
            }
        },
        placeDescription: {
            environments: {
                label: "Ambientes"
            },
            squareMeterCovered: {
                label: "m2 cubiertos",
                placeholder: "Rango de metros cuadrados cuebiertos"
            },
            squareMeterTotal: {
                label: "m2 totales",
                placeholder: "Rango de metros cuadrados totales"
            },
            toilets: {
                label: "Baños"
            },
            toilete: {
                label: "Baño"
            },
            carPlaces: {
                label: "Entrada de vehículos"
            },
            coveredGarage: {
                view: {
                    label: "Cochera cubierta"
                },
                placeholder: "Cochera cubierta"
            },
            laundry: {
                view: {
                    label: "Lavadero"
                },
                placeholder: "Lavadero"
            },
            electricity: {
                view: {
                    label: "Luz"
                },
                placeholder: "Luz"
            },
            gas: {
                view: {
                    label: "Gas"
                },
                placeholder: "Gas"
            },
            waterNetworkConnection: {
                view: {
                    label: "Agua"
                },
                placeholder: "Agua"
            },
            sewerConnection: {
                view: {
                    label: "Cloacas"
                },
                placeholder: "Cloacas"
            },
            extraNotes: {
                placeholder: "Notas"
            },
        },
        estate: {
            create: {
                finish: "Finalizar alta de propiedad"
            },
            owner: {
                label: "Cliente"
            },
            placeId: {
                label: "Propiedad"
            },
            photos: {
                label: "Fotos"
            },
            status: {
                placeholder: "Estado de la publicación",
                label: "Publicación"
            },
            price: {
                label: "Precio",
                placeholder: "Ingrese el valor en dólares de la propiedad"
            },
            operation: {
                view: {
                    sublabel: "en"
                }
            },
            speech:{
                create: {
                    label: "Detalle de la publicación"
                },
                label:"Detalle de la propiedad"
            }
        },
        estateSearch: {
            search: {
                label: "Propiedades",
                sublabel: "Disponibles"
            },
            environments: {
                label: "Ambientes",
                placeholder: "Ingresa la cantidad de ambientes"
            },
            estateType: {
                placeholder: "Selecciona el tipo de inmueble que estás buscando",
                label: "Tipo de propiedad"
            },
            squareMeterCovered: {
                label: "m2 cubiertos",
                placeholder: "Rango de metros cuadrados cuebiertos"
            },
            squareMeterTotal: {
                label: "m2 totales",
                placeholder: "Rango de metros cuadrados totales"
            },
            toilets: {
                label: "Baños"
            },
            toilete: {
                label: "Baño"
            },
            carPlaces: {
                label: "Entrada de vehículos"
            },
            coveredGarage: {
                view: {
                    label: "Cochera cubierta"
                },
                placeholder: "Cochera cubierta"
            },
            laundry: {
                view: {
                    label: "Lavadero"
                },
                placeholder: "Lavadero"
            },
            electricity: {
                view: {
                    label: "Luz"
                },
                placeholder: "Luz"
            },
            gas: {
                view: {
                    label: "Gas"
                },
                placeholder: "Gas"
            },
            waterNetworkConnection: {
                view: {
                    label: "Agua"
                },
                placeholder: "Agua"
            },
            sewerConnection: {
                view: {
                    label: "Cloacas"
                },
                placeholder: "Cloacas"
            },
            extraNotes: {
                placeholder: "Notas"
            },
            create: {
                finish: "Finalizar alta de propiedad"
            },
            owner: {
                label: "Cliente"
            },
            placeId: {
                label: "Propiedad"
            },
            photos: {
                label: "Fotos"
            },
            status: {
                placeholder: "Estado de la publicación",
                label: "Publicación"
            },
            operation: {
                view: {
                    sublabel: "en"
                }
            },
            price: {
                label: "Precio",
                placeholder: "Ingrese el valor en dólares de la propiedad"
            }
        },
        owner: {
            address: {
                sublabel: "Dirección del cliente"
            },
            cuit: {
                placeholder: "Ingrese el CUIT o CUIL del cliente",
                label: "CUIT/CUIL"
            },
            dni: {
                placeholder: "Ingrese el DNI del cliente",
                label: "DNI"
            }
        },
        administrator: {
            password: {
                label: "Contraseña"
            },
            username: {
                label: "Nombre de usuario"
            }
        },
        logIn: {
            create: {
                label: "Acceso",
                finish: "Ingresar"
            }
        },
        java: {
            lang: {
                Boolean: {
                    null: "No",
                    true: "Si",
                    false: "No"
                },
                Integer: {
                    null: "--"
                },
                String: {
                    "": "--",
                    null: "--"
                }
            }
        },
        app: {
            backend: {
                model: {
                    enums: {
                        EstateType: {
                            ...EstateTypeTranslations,
                            shortNames:{
                                ...EstateTypeTranslations,
                                DEPARTAMENTO: "Depto"
                            }
                        },
                        EstateOperations: {
                            SALE: 'Venta',
                            RENT: 'Alquiler',
                            null: 'No definido'
                        },
                        SquareMeterRange: {
                            UP_TO_20: "Hasta 20",
                            UP_TO_40: "20 a 40",
                            UP_TO_80: "40 a 80",
                            UP_TO_150: "80 a 150",
                            MORE_THAN_150: "Mas de 150",
                            null: "--"
                        },
                        EstateStatus: {
                            null: "No definido",
                            AVAILABLE: "Disponible",
                            RESERVED: "Reservada",
                            NOT_AVAILABLE: "No Disponible"
                        }
                    }
                }
            }
        },
        lexicalConnectors:{
            on: "en"
        },
        publicDetail:{
            value: "VALOR"
        }
    }
}
