import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './insurance-company-info.reducer';

export const InsuranceCompanyInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const insuranceCompanyInfoList = useAppSelector(state => state.insuranceCompanyInfo.entities);
  const loading = useAppSelector(state => state.insuranceCompanyInfo.loading);

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
      <h2 id="insurance-company-info-heading" data-cy="InsuranceCompanyInfoHeading">
        <Translate contentKey="tfbitaApp.insuranceCompanyInfo.home.title">Insurance Company Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.insuranceCompanyInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/insurance-company-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.insuranceCompanyInfo.home.createLabel">Create new Insurance Company Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {insuranceCompanyInfoList && insuranceCompanyInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.insuranceCompanyInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('modificationDate')}>
                  <Translate contentKey="tfbitaApp.insuranceCompanyInfo.modificationDate">Modification Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('modificationDate')} />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="tfbitaApp.insuranceCompanyInfo.name">Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.insuranceCompanyInfo.insuranceCompanyInfo">Insurance Company Info</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {insuranceCompanyInfoList.map((insuranceCompanyInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/insurance-company-info/${insuranceCompanyInfo.id}`} color="link" size="sm">
                      {insuranceCompanyInfo.id}
                    </Button>
                  </td>
                  <td>{insuranceCompanyInfo.modificationDate}</td>
                  <td>{insuranceCompanyInfo.name}</td>
                  <td>
                    {insuranceCompanyInfo.insuranceCompanyInfo ? (
                      <Link to={`/draft/${insuranceCompanyInfo.insuranceCompanyInfo.id}`}>
                        {insuranceCompanyInfo.insuranceCompanyInfo.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/insurance-company-info/${insuranceCompanyInfo.id}`}
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
                        to={`/insurance-company-info/${insuranceCompanyInfo.id}/edit`}
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
                        onClick={() => (window.location.href = `/insurance-company-info/${insuranceCompanyInfo.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.insuranceCompanyInfo.home.notFound">No Insurance Company Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default InsuranceCompanyInfo;
