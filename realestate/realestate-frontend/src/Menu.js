import React from 'react';
import { Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';
import Carousel from 'react-elastic-carousel';

/**
 * @param {Array} options
 */
const Menu = ({options}) => {

    const showLogin = localStorage.getItem("arena-administrator") === undefined || localStorage.getItem("arena-administrator") === null;

    const items = [
        <div className='item' style={{backgroundImage:'url(/banner/es/1.jpg)'}}>
            <div className="title">encontrá la PROPIEDAD que NECESITAS</div>
        </div>,
        <div className='item' style={{backgroundImage:'url(/banner/es/2.jpg)'}}>
            <div className="title">un LUGAR para vos y tu FAMILIA</div>
        </div>,
        <div className='item' style={{backgroundImage:'url(/banner/es/3.jpg)'}}>
            <div className="title">un DESCANSO para tomar IMPULSO</div>
        </div>,
    ];
    const isPublicSearch=true;
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light navbar navbar-default navbar-fixed-top" style={{fontFamily: 'Roboto Cn'}}>
                <div className='container'>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <a className="navbar-brand m-auto" href="/"><img style={{width:'80px'}} src='/LogoCostoya.svg'></img></a>

                    {   !showLogin &&
                        <div className="collapse navbar-collapse " id="navbarSupportedContent">
                            <ul className="navbar-nav text-left m-auto" >
                                {
                                    options.map((option, i)=>{
                                        return(
                                            <li data-toggle="collapse" data-target="#navbarSupportedContent" key={i} className="nav-item">
                                                <Link   className="nav-link"  to={option.href}>{option.label}</Link>
                                            </li>
                                        )
                                    })
                                }
                            </ul>
                        </div>
                    }
                    {
                        !showLogin &&
                        <a className="logout my-auto" href="/"
                            onClick={
                                ()=>{
                                    localStorage.removeItem('arena-authorization'); 
                                    localStorage.removeItem('arena-administrator'); 
                                }
                            }
                        ><FontAwesomeIcon icon={faSignOutAlt}></FontAwesomeIcon></a>
                    }
                    {
                        showLogin &&
                        <a className="login my-auto" href="/build/container/create/logIn">Ingresar</a>
                    }
                </div>
                
            </nav>
                {
                    isPublicSearch && 
                    <div className='marketing-carousel'>
                        <Carousel initialActiveIndex={0}showArrows={false} pagination={false} enableAutoPlay={true} transitionMs={1000} autoPlaySpeed={6000} itemsToShow={1} showEmptySlots={false}>
                            {items}
                            {items}
                        </Carousel>
                    </div>
                }
        </div>
        )

}

export default Menu;