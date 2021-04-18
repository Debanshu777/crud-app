import React,{useState} from 'react';
import * as FaIcons from 'react-icons/fa';
import {Link} from 'react-router-dom';
import {SideBarData} from './SideBarData';
import './NavBar.css';


function NavBar() {
    const [sidebar,setSidebar] =useState(false);

    const showSideBar = ()=>setSidebar(!sidebar);
    return (
        <>
            <div className="navbar">
                <Link to="#" classNmae='menu-bars'>
                    <ul className='nav-menu-items' >
                        <li className='navbar-toggle' onClick={showSideBar}>
                            <Link to="#" >
                                <FaIcons.FaBars/>
                            </Link>
                        </li>
                        {SideBarData.map((item,index)=>{
                        return (
                            <li key={index} className='nav-icon'>
                                <Link to={item.path}>
                                    {item.icon}
                                </Link>
                            </li>
                        )
                    })}
                    </ul>
                </Link>
            </div>
            <nav className={sidebar ? 'nav-menu active' :'nav-menu'}>
                <ul className='nav-menu-items' onClick={showSideBar}>
                    <li className='navbar-toggle'>
                        <Link to="#" className='menu-bars'>
                            <FaIcons.FaBars/>
                        </Link>
                    </li>
                    {SideBarData.map((item,index)=>{
                        return (
                            <li key={index} className={item.cName}>
                                <Link to={item.path}>
                                    {item.icon}
                                    <span>{item.title}</span>
                                </Link>
                            </li>
                        )
                    })}
                </ul>
            </nav>
        </>
    )
}

export default NavBar
