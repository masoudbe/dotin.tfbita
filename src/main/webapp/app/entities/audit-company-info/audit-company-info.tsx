import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './audit-company-info.reducer';

export const AuditCompanyInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const auditCompanyInfoList = useAppSelector(state => state.auditCompanyInfo.entities);
  const loading = useAppSelector(state => state.auditCompanyInfo.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="audit-company-info-heading" data-cy="AuditCompanyInfoHeading">
        <Translate contentKey="tfbitaApp.auditCompanyInfo.home.title">Audit Company Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.auditCompanyInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/audit-company-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.auditCompanyInfo.home.createLabel">Create new Audit Company Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {auditCompanyInfoList && auditCompanyInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('address')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.address">Address</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('address')} />
                </th>
                <th className="hand" onClick={sort('barCodes')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.barCodes">Bar Codes</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('barCodes')} />
                </th>
                <th className="hand" onClick={sort('dateOfRegistration')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.dateOfRegistration">Date Of Registration</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dateOfRegistration')} />
                </th>
                <th className="hand" onClick={sort('fax')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.fax">Fax</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fax')} />
                </th>
                <th className="hand" onClick={sort('floor')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.floor">Floor</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('floor')} />
                </th>
                <th className="hand" onClick={sort('inernationalobserviation')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.inernationalobserviation">Inernationalobserviation</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('inernationalobserviation')} />
                </th>
                <th className="hand" onClick={sort('mainStreet')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.mainStreet">Main Street</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mainStreet')} />
                </th>
                <th className="hand" onClick={sort('mobile')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.mobile">Mobile</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mobile')} />
                </th>
                <th className="hand" onClick={sort('plaque')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.plaque">Plaque</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('plaque')} />
                </th>
                <th className="hand" onClick={sort('postalCode')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.postalCode">Postal Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postalCode')} />
                </th>
                <th className="hand" onClick={sort('registrationNumber')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.registrationNumber">Registration Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('registrationNumber')} />
                </th>
                <th className="hand" onClick={sort('telephone')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.telephone">Telephone</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('telephone')} />
                </th>
                <th className="hand" onClick={sort('tempId')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.tempId">Temp Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tempId')} />
                </th>
                <th className="hand" onClick={sort('theSideStreet')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.theSideStreet">The Side Street</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('theSideStreet')} />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.title">Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('title')} />
                </th>
                <th className="hand" onClick={sort('unit')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.unit">Unit</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('unit')} />
                </th>
                <th className="hand" onClick={sort('cityCode')}>
                  <Translate contentKey="tfbitaApp.auditCompanyInfo.cityCode">City Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cityCode')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {auditCompanyInfoList.map((auditCompanyInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/audit-company-info/${auditCompanyInfo.id}`} color="link" size="sm">
                      {auditCompanyInfo.id}
                    </Button>
                  </td>
                  <td>{auditCompanyInfo.address}</td>
                  <td>{auditCompanyInfo.barCodes}</td>
                  <td>{auditCompanyInfo.dateOfRegistration}</td>
                  <td>{auditCompanyInfo.fax}</td>
                  <td>{auditCompanyInfo.floor}</td>
                  <td>{auditCompanyInfo.inernationalobserviation}</td>
                  <td>{auditCompanyInfo.mainStreet}</td>
                  <td>{auditCompanyInfo.mobile}</td>
                  <td>{auditCompanyInfo.plaque}</td>
                  <td>{auditCompanyInfo.postalCode}</td>
                  <td>{auditCompanyInfo.registrationNumber}</td>
                  <td>{auditCompanyInfo.telephone}</td>
                  <td>{auditCompanyInfo.tempId}</td>
                  <td>{auditCompanyInfo.theSideStreet}</td>
                  <td>{auditCompanyInfo.title}</td>
                  <td>{auditCompanyInfo.unit}</td>
                  <td>{auditCompanyInfo.cityCode}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/audit-company-info/${auditCompanyInfo.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/audit-company-info/${auditCompanyInfo.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/audit-company-info/${auditCompanyInfo.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.home.notFound">No Audit Company Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AuditCompanyInfo;
