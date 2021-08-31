import React from 'react';
import { Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons'

/**
 * @param {Array} options
 */
const Menu = ({options}) => {

    const showLogin = localStorage.getItem("arena-administrator") === undefined || localStorage.getItem("arena-administrator") === null;

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light navbar navbar-default navbar-fixed-top" style={{fontFamily: 'Roboto Cn'}}>
            <div className='container'>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <a className="navbar-brand" href="https://costoya.com.ar/"><img style={{width:'80px'}} src='/LogoCostoya.svg'></img></a>

            {   !showLogin &&
                <div className="collapse navbar-collapse " id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto text-left mt-3" >
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
                <a className="navbar-brand logout" href="/"
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
                <a className="navbar-brand login" href="/build/container/create/logIn">Ingresar</a>
            }
            </div>
            
        </nav>)
}

export default Menu;