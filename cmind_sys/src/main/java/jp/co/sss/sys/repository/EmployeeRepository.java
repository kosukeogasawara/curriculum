//CRUD = 登録・削除・更新・参照機能
//このクラスでテーブルが存在するか確認している

package jp.co.sss.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.sys.entity.Employee;

/**
 * リポジトリークラス
 * @author Inoue Nami
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}