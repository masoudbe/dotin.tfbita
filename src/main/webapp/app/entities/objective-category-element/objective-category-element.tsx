import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './objective-category-element.reducer';

export const ObjectiveCategoryElement = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const objectiveCategoryElementList = useAppSelector(state => state.objectiveCategoryElement.entities);
  const loading = useAppSelector(state => state.objectiveCategoryElement.loading);

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
      <h2 id="objective-category-element-heading" data-cy="ObjectiveCategoryElementHeading">
        <Translate contentKey="tfbitaApp.objectiveCategoryElement.home.title">Objective Category Elements</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.objectiveCategoryElement.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/objective-category-element/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.objectiveCategoryElement.home.createLabel">Create new Objective Category Element</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {objectiveCategoryElementList && objectiveCategoryElementList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.objectiveCategoryElement.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('entityClass')}>
                  <Translate contentKey="tfbitaApp.objectiveCategoryElement.entityClass">Entity Class</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('entityClass')} />
                </th>
                <th className="hand" onClick={sort('entityId')}>
                  <Translate contentKey="tfbitaApp.objectiveCategoryElement.entityId">Entity Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('entityId')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.objectiveCategoryElement.objectiveCategory">Objective Category</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {objectiveCategoryElementList.map((objectiveCategoryElement, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/objective-category-element/${objectiveCategoryElement.id}`} color="link" size="sm">
                      {objectiveCategoryElement.id}
                    </Button>
                  </td>
                  <td>{objectiveCategoryElement.entityClass}</td>
                  <td>{objectiveCategoryElement.entityId}</td>
                  <td>
                    {objectiveCategoryElement.objectiveCategory ? (
                      <Link to={`/objective-category/${objectiveCategoryElement.objectiveCategory.id}`}>
                        {objectiveCategoryElement.objectiveCategory.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/objective-category-element/${objectiveCategoryElement.id}`}
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
                        to={`/objective-category-element/${objectiveCategoryElement.id}/edit`}
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
                        onClick={() => (window.location.href = `/objective-category-element/${objectiveCategoryElement.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.objectiveCategoryElement.home.notFound">No Objective Category Elements found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ObjectiveCategoryElement;
