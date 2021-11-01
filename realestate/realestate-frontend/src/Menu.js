import React from 'react';
import { Link, useLocation } from 'react-router-dom'
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
            <div className="title">
                <img src='/banner/es/text.1.svg'></img>
            </div>
        </div>,
        <div className='item' style={{backgroundImage:'url(/banner/es/2.jpg)'}}>
            <div className="title">
                <img src='/banner/es/text.2.svg'></img>
            </div>
        </div>,
        <div className='item' style={{backgroundImage:'url(/banner/es/3.jpg)'}}>
            <div className="title">
                <img src='/banner/es/text.3.svg'></img>
            </div>
        </div>,
    ];
    const location = useLocation();
    const isPublicSearch=location.search && location.search.includes("publicSearch") ;
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar navbar-default navbar-fixed-top" style={{fontFamily: 'Roboto Cn'}}>
                <div className='container'>
                    <a className="navbar-brand" href="/"><img  src='/LogoCostoya.png'></img></a>
                    <button className="navbar-toggler p-1 mr-2" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                        <div className="collapse navbar-collapse " id="navbarSupportedContent">
                            <ul className="navbar-nav text-left ml-3" >
                                {   !showLogin &&
                                    options.map((option, i)=>{
                                        return(
                                            <li data-toggle="collapse" data-target="#navbarSupportedContent" key={i} className="nav-item">
                                                <Link   className="nav-link"  to={option.href}>{option.label}</Link>
                                            </li>
                                        )
                                    })
                                }
                                {
                                    !showLogin &&
                                    <li data-toggle="collapse" data-target="#navbarSupportedContent" key="logout" className="nav-item">
                                        <a className="logout nav-link my-auto" href="/"
                                            onClick={
                                                ()=>{
                                                    localStorage.removeItem('arena-authorization'); 
                                                    localStorage.removeItem('arena-administrator'); 
                                                }
                                            }
                                        >Salir <FontAwesomeIcon icon={faSignOutAlt}></FontAwesomeIcon></a>
                                    </li>
                                }
                                {
                                    showLogin &&
                                    <li data-toggle="collapse" data-target="#navbarSupportedContent" key="login" className="nav-item">
                                        <a className="nav-link login my-auto" href="/build/container/create/logIn">Ingresar</a>
                                    </li>
                                }
                            </ul>
                        </div>
                    
                </div>
                
            </nav>
                {
                    isPublicSearch && 
                    <div className='marketing-carousel'>
                        <Carousel initialActiveIndex={0}showArrows={false} pagination={false} enableAutoPlay={true} transitionMs={1000} autoPlaySpeed={6000} itemsToShow={1} showEmptySlots={false}>
                            {items}
                            {items}
                            {items[0]}
                        </Carousel>
                    </div>
                }
        </div>
        )

}

export default Menu;