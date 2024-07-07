import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './type-attribute.reducer';

export const TypeAttribute = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const typeAttributeList = useAppSelector(state => state.typeAttribute.entities);
  const loading = useAppSelector(state => state.typeAttribute.loading);

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
      <h2 id="type-attribute-heading" data-cy="TypeAttributeHeading">
        <Translate contentKey="tfbitaApp.typeAttribute.home.title">Type Attributes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.typeAttribute.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/type-attribute/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.typeAttribute.home.createLabel">Create new Type Attribute</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {typeAttributeList && typeAttributeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.typeAttribute.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('necessary')}>
                  <Translate contentKey="tfbitaApp.typeAttribute.necessary">Necessary</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('necessary')} />
                </th>
                <th className="hand" onClick={sort('isUnique')}>
                  <Translate contentKey="tfbitaApp.typeAttribute.isUnique">Is Unique</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isUnique')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.typeAttribute.attribute">Attribute</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.typeAttribute.productType">Product Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {typeAttributeList.map((typeAttribute, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/type-attribute/${typeAttribute.id}`} color="link" size="sm">
                      {typeAttribute.id}
                    </Button>
                  </td>
                  <td>{typeAttribute.necessary ? 'true' : 'false'}</td>
                  <td>{typeAttribute.isUnique ? 'true' : 'false'}</td>
                  <td>
                    {typeAttribute.attribute ? (
                      <Link to={`/attribute/${typeAttribute.attribute.id}`}>{typeAttribute.attribute.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {typeAttribute.productTypes
                      ? typeAttribute.productTypes.map((val, j) => (
                          <span key={j}>
                            <Link to={`/product-type/${val.id}`}>{val.id}</Link>
                            {j === typeAttribute.productTypes.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/type-attribute/${typeAttribute.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/type-attribute/${typeAttribute.id}/edit`}
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
                        onClick={() => (window.location.href = `/type-attribute/${typeAttribute.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.typeAttribute.home.notFound">No Type Attributes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default TypeAttribute;
