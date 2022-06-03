import React from "react";

export default class Sidebar{
    render() {
        return (
            <aside className="main-sidebar sidebar-dark-primary elevation-4">
                <a href="../index3.html" className="brand-link">
                    <img src="../dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
                         className="brand-image img-circle elevation-3" style="opacity: .8"></img>
                        <span className="brand-text font-weight-light">Administrator Page</span>
                </a>

                <div className="sidebar">
                    <div className="user-panel mt-3 pb-3 mb-3 d-flex">
                        <div className="image">
                            <img src="../dist/img/user3-128x128.jpg" className="img-circle elevation-2"
                                 alt="User Image"></img>
                        </div>
                        <div className="info">
                            <a href="#" className="d-block">Hello Admin</a>
                        </div>
                    </div>

                    <div className="form-inline">
                        <div className="input-group" data-widget="sidebar-search">
                            <input className="form-control form-control-sidebar" type="search" placeholder="Search"
                                   aria-label="Search"></input>
                                <div className="input-group-append">
                                    <button className="btn btn-sidebar">
                                        <i className="fas fa-search fa-fw"></i>
                                    </button>
                                </div>
                        </div>
                    </div>

                    <nav className="mt-2">
                        <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                            data-accordion="false">
                            <li className="nav-item">
                                <a href="#" className="nav-link">
                                    <i className="nav-icon fas fa-tachometer-alt"></i>
                                    <p>
                                        Dashboard
                                    </p>
                                </a>
                            </li>
                            <li className="nav-item">
                                <a href="/admin/students/list" className="nav-link">
                                    <i className="nav-icon fas fa-solid fa-graduation-cap"></i>
                                    <p>
                                        Student Management
                                        <i className="fas fa-angle-left right"></i>
                                    </p>
                                </a>
                                <ul className="nav nav-treeview">
                                    <li className="nav-item">
                                        <a href="/admin/students/create" className="nav-link">
                                            <i className="far fa-circle nav-icon"></i>
                                            <p>Create new</p>
                                        </a>
                                    </li>
                                    <li className="nav-item">
                                        <a href="/admin/students/list" className="nav-link">
                                            <i className="far fa-circle nav-icon"></i>
                                            <p>List student</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">
                                    <i className="nav-icon fas fa-copy"></i>
                                    <p>
                                        Faculty Management
                                        <i className="fas fa-angle-left right"></i>
                                    </p>
                                </a>
                                <ul className="nav nav-treeview">
                                    <li className="nav-item">
                                        <a href="../layout/top-nav.html" className="nav-link">
                                            <i className="far fa-circle nav-icon"></i>
                                            <p>Create New</p>
                                        </a>
                                    </li>
                                    <li className="nav-item">
                                        <a href="../layout/top-nav-sidebar.html" className="nav-link">
                                            <i className="far fa-circle nav-icon"></i>
                                            <p>List Faculty</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
        );
    }
}